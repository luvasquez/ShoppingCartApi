package sv.com.cuscatlan.shoppingcart.service.mapper;

import org.mapstruct.Mapper;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderDetailRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderDetailResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderResponse;
import sv.com.cuscatlan.shoppingcart.model.Order;
import sv.com.cuscatlan.shoppingcart.model.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail toOrderDetail(final OrderDetailRequest orderDetailRequest);

    OrderDetailResponse toOrderResponse(final OrderDetail orderDetail);
}