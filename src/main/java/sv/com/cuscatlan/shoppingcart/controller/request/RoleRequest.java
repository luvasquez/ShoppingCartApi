package sv.com.cuscatlan.shoppingcart.controller.request;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    private Integer id;
    private String name;
}
