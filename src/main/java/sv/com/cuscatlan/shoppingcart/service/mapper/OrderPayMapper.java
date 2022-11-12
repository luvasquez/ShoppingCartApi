package sv.com.cuscatlan.shoppingcart.service.mapper;

import org.mapstruct.Mapper;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderPayRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderPayResponse;
import sv.com.cuscatlan.shoppingcart.model.OrderPay;

@Mapper(componentModel = "spring")
public interface OrderPayMapper {

    OrderPay toOrderPay(final OrderPayRequest orderPayRequest);

    OrderPayResponse toOrderPayResponse(final OrderPay orderPay);
}
