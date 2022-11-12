package sv.com.cuscatlan.shoppingcart.service.mapper;

import org.mapstruct.Mapper;
import sv.com.cuscatlan.shoppingcart.controller.request.RoleRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.RoleResponse;
import sv.com.cuscatlan.shoppingcart.model.Role;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Role toRole(final RoleRequest roleRequest);

    RoleResponse toRoleResponse(final Role role);

}
