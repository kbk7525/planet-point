package project.backend.dto;

import lombok.*;
import project.backend.domain.Payment;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    @NonNull
    private String paymentType;

    @NonNull
    private Long amount;

    @NonNull
    private String orderName;

    private String customerSuccessUrl;

    private String customerFailUrl;

    public Payment toEntity() {
        return Payment.builder()
                .paymentType((paymentType))
                .amount(amount)
                .orderName(orderName)
                .orderId(UUID.randomUUID().toString())
                .paySuccessYN(false)
                .build();
    }
}
