package sv.com.cuscatlan.shoppingcart.controller.request;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    private Integer id;
    private String name;
    private String lastName;
    private String dui;
    private String email;
    private String address;
}
