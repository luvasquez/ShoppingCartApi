package sv.com.cuscatlan.shoppingcart.controller.response;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderPayResponse {
    private Integer orderId;

    private String cardNumber;

    private String cardHolderName;

    private String cardExpiryMm;

    private String cardExpiryYy;

    private String cardCvv;

    private String status;
}
