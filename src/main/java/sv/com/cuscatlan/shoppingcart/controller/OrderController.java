package sv.com.cuscatlan.shoppingcart.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderPayRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderPayResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderResponse;
import sv.com.cuscatlan.shoppingcart.service.OrderService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor()
public class OrderController {

    @NonNull
    private final OrderService orderService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public OrderResponse getOrder(@PathVariable Integer id) {
        return orderService.getOrderDetail(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OrderResponse saveOrder(@Valid @RequestBody final OrderRequest orderRequest) {
        return orderService.saveOrder(orderRequest);
    }

    @PutMapping
    @ResponseStatus(OK)
    public OrderResponse updateOrder(@Valid @RequestBody final OrderRequest orderRequest) {
        return orderService.updateOrder(orderRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/checkout")
    @ResponseStatus(OK)
    public OrderPayResponse checkoutOrder(@Valid @RequestBody final OrderPayRequest orderPayRequest) {
        return orderService.checkoutPay(orderPayRequest);
    }

    @PutMapping("/pay/{idOrderPay}")
    @ResponseStatus(OK)
    public OrderPayResponse payOrder(@PathVariable Integer idOrderPay) {
        return orderService.processPay(idOrderPay);
    }

    @PutMapping("sendArticles/{idOrderPay}")
    @ResponseStatus(OK)
    public void sendArticles(@PathVariable Integer idOrderPay) {
        orderService.sendArticlesOrderToAdress(idOrderPay);
    }

}
