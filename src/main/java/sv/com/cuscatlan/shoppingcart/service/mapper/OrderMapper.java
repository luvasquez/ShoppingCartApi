package sv.com.cuscatlan.shoppingcart.service.mapper;

import org.mapstruct.Mapper;
import sv.com.cuscatlan.shoppingcart.controller.request.OrderRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.OrderResponse;
import sv.com.cuscatlan.shoppingcart.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(final OrderRequest orderRequest);

    OrderResponse toOrderResponse(final Order order);
}
