package sv.com.cuscatlan.shoppingcart.controller.request;

import lombok.*;
import sv.com.cuscatlan.shoppingcart.model.Role;

import java.util.Collection;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;
    private String userName;
    private String password;
    private Collection<Role> roles;
}
