package sv.com.cuscatlan.shoppingcart.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.com.cuscatlan.shoppingcart.controller.request.RoleRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.UserRequest;
import sv.com.cuscatlan.shoppingcart.controller.request.UserRoleRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.RoleResponse;
import sv.com.cuscatlan.shoppingcart.controller.response.UserResponse;
import sv.com.cuscatlan.shoppingcart.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor()
public class SecurityController {

    @NonNull
    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(OK)
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{nameUser}")
    @ResponseStatus(OK)
    public UserResponse getUser(@PathVariable String nameUser) {
        return userService.getUser(nameUser);
    }

    @PostMapping("/user")
    @ResponseStatus(CREATED)
    public UserResponse saveUser(@Valid @RequestBody final UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @PostMapping("/role")
    @ResponseStatus(CREATED)
    public RoleResponse saveRol(@Valid @RequestBody final RoleRequest roleRequest) {
        return userService.saveRol(roleRequest);
    }

    @PostMapping("/role/addtouser")
    @ResponseStatus(OK)
    public void saveRol(@Valid @RequestBody final UserRoleRequest userRoleRequest) {
         userService.addRoleToUser(userRoleRequest.getUserName(), userRoleRequest.getRolName());
    }
}
