package sv.com.cuscatlan.shoppingcart.controller.request;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {

    private String userName;
    private String rolName;

}
