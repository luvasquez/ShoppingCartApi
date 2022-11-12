package sv.com.cuscatlan.shoppingcart.service.mapper;

import org.mapstruct.Mapper;
import sv.com.cuscatlan.shoppingcart.controller.request.UserRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.UserResponse;
import sv.com.cuscatlan.shoppingcart.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(final UserRequest userRequest);

    UserResponse toUserResponse(final User user);
}
