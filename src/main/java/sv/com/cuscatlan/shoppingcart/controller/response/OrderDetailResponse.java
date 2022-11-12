package sv.com.cuscatlan.shoppingcart.controller.response;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private Integer idProduct;
    private String productDetail;
    private int quantity;
    private Double amount;
}
