package project.backend.dto;

import lombok.*;
import project.backend.config.DateConfig;
import project.backend.domain.Payment;

import java.util.UUID;

//토스쪽으로 결제요청을 할 때 사용하는 dto
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
                .orderId((UUID.randomUUID()).toString()) //백엔드 쪽에서 랜덤값 지정해서 저장
                .payType("카드")
                .amount(amount)
                .orderName(orderName)
                .userEmail(userEmail)
                .userName(userName)
                .paySuccessYn("N") //성공여부 초기설정 no
                .createDate(new DateConfig().getNowDate())
                .build();
    }
}
