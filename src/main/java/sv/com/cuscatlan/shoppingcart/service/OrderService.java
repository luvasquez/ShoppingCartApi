package sv.com.cuscatlan.shoppingcart.service;

import sv.com.cuscatlan.shoppingcart.controller.request.OrderPayRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderPayResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderResponse;

public interface OrderService {

    OrderResponse getOrderDetail(final Integer id);

    OrderResponse saveOrder(final OrderRequest orderRequest);

    OrderResponse updateOrder(final OrderRequest orderRequest);

    void deleteOrder(final Integer id);

    OrderPayResponse checkoutPay(final OrderPayRequest orderPayRequest);

    OrderPayResponse processPay(final Integer orderPayId);

    void sendArticlesOrderToAdress(final Integer orderPayId);

}
