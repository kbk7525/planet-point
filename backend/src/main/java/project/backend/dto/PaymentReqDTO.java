package project.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.backend.config.DateConfig;
import project.backend.domain.Payment;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReqDTO {

    private String payType;

    private Long amount;

    private String orderName;

    private String userEmail;

    private String userName;

    public Payment toEntity() {
        return Payment.builder()
                .orderId((UUID.randomUUID()).toString())
                .payType("카드")
                .amount(amount)
                .orderName(orderName)
                .userEmail(userEmail)
                .userName(userName)
                .createDate(String.valueOf(new DateConfig().getNowDate()))
                .build();
    }
}
