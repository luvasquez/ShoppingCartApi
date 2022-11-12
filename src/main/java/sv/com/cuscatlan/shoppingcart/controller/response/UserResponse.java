package sv.com.cuscatlan.shoppingcart.controller.response;

import lombok.*;
import sv.com.cuscatlan.shoppingcart.model.Role;

import java.util.Collection;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse{
    private Integer id;
    private String userName;
    private String password;
    private Collection<Role> roles;
}
