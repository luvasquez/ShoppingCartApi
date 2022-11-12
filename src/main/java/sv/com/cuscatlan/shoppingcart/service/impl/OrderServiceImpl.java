package sv.com.cuscatlan.shoppingcart.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderDetailRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderPayRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderDetailResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderPayResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.ProductResponse;
import sv.com.cuscatlan.shoppingcart.model.Client;
import sv.com.cuscatlan.shoppingcart.model.Order;
import sv.com.cuscatlan.shoppingcart.model.OrderDetail;
import sv.com.cuscatlan.shoppingcart.model.OrderPay;
import sv.com.cuscatlan.shoppingcart.repository.ClientRepository;
import sv.com.cuscatlan.shoppingcart.repository.OrderDetailRepository;
import sv.com.cuscatlan.shoppingcart.repository.OrderPayRepository;
import sv.com.cuscatlan.shoppingcart.repository.OrderRepository;
import sv.com.cuscatlan.shoppingcart.service.OrderService;
import sv.com.cuscatlan.shoppingcart.service.ProductService;
import sv.com.cuscatlan.shoppingcart.service.mapper.OrderMapper;
import sv.com.cuscatlan.shoppingcart.service.mapper.OrderPayMapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @NonNull
    private final OrderRepository orderRepository;

    @NonNull
    private final OrderDetailRepository orderDetailRepository;

    @NonNull
    private final ClientRepository clientRepository;

    @NonNull
    private final OrderPayRepository orderPayRepository;

    @NonNull
    private final ProductService productService;

    @NonNull
    private final OrderMapper orderMapper;

    @NonNull
    private final OrderPayMapper orderPayMapper;

    @Override
    public OrderResponse getOrderDetail(Integer id) {
        log.info(">> fetching the order id {} ", id);
        return orderMapper.toOrderResponse(
                orderRepository.findById(id).
                        orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        List<OrderDetailRequest> orderProducts = orderRequest.getProducts();
        Client client = clientRepository.findById(orderRequest.getIdClient())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        Order order = new Order();
        order.setClient(client);
        order.setOrderDescription("status: new order in progress...");

        List<OrderDetail> details = new ArrayList<>();
        OrderDetail dt;
        ProductResponse product;
        Double amount = 0.0;
        for(OrderDetailRequest or: orderProducts) {
            product = productService.findById(or.getIdProduct());
            amount = or.getQuantity() * product.getPrice();

            dt = new OrderDetail();
            dt.setIdProduct(or.getIdProduct());
            dt.setProductDetail(product.getTitle());
            dt.setQuantity(or.getQuantity());
            dt.setAmount(amount);
            details.add(dt);
        }

        order.setDetails(details);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest) {
        Order order = orderRepository.findByClientId(orderRequest.getIdClient())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        List<OrderDetail> updateItems = new ArrayList<>();
        OrderDetail detail;
        ProductResponse product;


        log.info(">> Adding new items to cart");
        for(OrderDetailRequest odr : orderRequest.getProducts()) {
            detail = null;
            Double amount = 0.0;
            boolean exist = order.getDetails()
                    .stream().anyMatch(o -> o.getIdProduct().equals(odr.getIdProduct()));

            if(!exist) {
                product = productService.findById(odr.getIdProduct());
                amount = odr.getQuantity() * product.getPrice();

                detail = new OrderDetail();
                detail.setIdProduct(product.getId());
                detail.setProductDetail(product.getTitle());
                detail.setAmount(amount);
                detail.setQuantity(odr.getQuantity());

                updateItems.add(detail);
            }
        }

        log.info(">> updating items to cart");
        for(OrderDetail od : order.getDetails()) {
            detail = null;

            Double amount = 0.0;
            for(OrderDetailRequest odr : orderRequest.getProducts()) {
                if(od.getIdProduct().equals(odr.getIdProduct())) {
                    product = productService.findById(odr.getIdProduct());
                    amount = odr.getQuantity() * product.getPrice();
                    detail = od;
                    detail.setAmount(amount);
                    detail.setQuantity(odr.getQuantity());
                    break;
                }
            }

            if(detail != null) {
                updateItems.add(detail);
            }
        }

        order.setDetails(updateItems);

        List<OrderDetail> removeItems = orderDetailRepository.findOrderDetailsByIdNotIn(
                updateItems.stream().map(OrderDetail::getId)
                        .collect(Collectors.toList()));

        orderDetailRepository.deleteAll(removeItems);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderPayResponse checkoutPay(OrderPayRequest orderPayRequest) {
        Order order = orderRepository.findById(orderPayRequest.getOrderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        OrderPay orderPay = new OrderPay();
        orderPay.setOrder(order);
        orderPay.setCardNumber(orderPayRequest.getCardNumber());
        orderPay.setCardHolderName(orderPayRequest.getCardHolderName());
        orderPay.setCardExpiryMm(orderPayRequest.getCardExpiryMm());
        orderPay.setCardExpiryYy(orderPayRequest.getCardExpiryYy());
        orderPay.setCardCvv(orderPayRequest.getCardCvv());
        orderPay.setStatus("checkout, pay in process");

        return orderPayMapper.toOrderPayResponse(orderPayRepository.save(orderPay));
    }

    @Override
    public OrderPayResponse processPay(Integer orderPayId) {
        log.info(">> processing pay, try connect to server the credit cart");
        OrderPay orderPay = orderPayRepository.findById(orderPayId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No data credit found"));

        log.info(">> sending info, try connect to server the credit cart");
        if(serviceCorrectPay()) {
            orderPay.setStatus("Order payed");
            orderPay.getOrder().setOrderDescription("status: order payed");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad card information");
        }
        return orderPayMapper.toOrderPayResponse(orderPayRepository.save(orderPay));
    }

    @Override
    public void sendArticlesOrderToAdress(Integer orderPayId) {
        OrderPay orderPay = orderPayRepository.findById(orderPayId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No data credit found"));

        orderPay.getOrder().setOrderDescription("status: articles send to address");
        orderPayRepository.save(orderPay);
    }

    private boolean serviceCorrectPay() {
        return true;
    }

}
