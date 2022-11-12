package sv.com.cuscatlan.shoppingcart.controller.response;

import lombok.*;
import sv.com.cuscatlan.shoppingcart.model.Client;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer id;
    private String orderDescription;
    private Client client;
    List<OrderDetailResponse> details;
}

