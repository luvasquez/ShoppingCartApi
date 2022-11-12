package sv.com.cuscatlan.shoppingcart.service;

import sv.com.cuscatlan.shoppingcart.controller.request.RoleRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.UserRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.RoleResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse save(final UserRequest userRequest);

    RoleResponse saveRol(final RoleRequest roleRequest);

    void addRoleToUser(final String userName, final String roleName);

    UserResponse getUser(final String userName);

    List<UserResponse> getUsers();

}
