package sv.com.cuscatlan.shoppingcart.controller.request;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private Integer idProduct;
    private Integer quantity;
}
