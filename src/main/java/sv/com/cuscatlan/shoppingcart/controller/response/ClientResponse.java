package sv.com.cuscatlan.shoppingcart.controller.response;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private Integer id;
    private String name;
    private String lastName;
    private String dui;
    private String email;
    private String address;
}
