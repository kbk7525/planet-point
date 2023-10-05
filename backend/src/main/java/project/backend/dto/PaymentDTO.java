package project.backend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private String payType;
    private Long amount;
    private String cardCompany;
    private String cardNumber;
    private String orderId;
    private String orderName;
    private String userName;
    private String userEmail;
    private String paymentKey;
    private String paySuccessYn;
    private String createDate;
}
