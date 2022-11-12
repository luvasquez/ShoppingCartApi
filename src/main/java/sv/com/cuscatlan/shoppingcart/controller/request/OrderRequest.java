package sv.com.cuscatlan.shoppingcart.controller.request;

import lombok.*;
import sv.com.cuscatlan.shoppingcart.model.Client;
import sv.com.cuscatlan.shoppingcart.model.OrderDetail;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Integer idClient;
    private List<OrderDetailRequest> products;
}

