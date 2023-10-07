package project.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import project.backend.dto.PaymentDTO;
import project.backend.dto.PaymentResDTO;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column
    private String payType;

    @Column
    private Long amount; //결제금액

    @Column
    @Setter
    private String issuerCode; //카드 발급사 코드

    @Column
    @Setter
    private String cardNumber; //카드번호

    @Column
    private String orderId; //주문고유번호

    @Column
    private String orderName; //상품명

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    @Setter
    private String paymentKey;

    @Column
    @Setter
    private String paySuccessYn; //결제 성공 여부

    @Column
    @Setter
    private String payFailReason; //결제 실패 이유

    @Column
    private String createDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    public PaymentResDTO toRes() {
        return PaymentResDTO.builder()
                .payType(payType)
                .paySuccessYn(paySuccessYn)
                .amount(amount)
                .orderId(orderId)
                .orderName(orderName)
                .userName(userName)
                .userEmail(userEmail)
                .createDate(createDate)
                .build();
    }
    public PaymentDTO toDTO() {
        return PaymentDTO.builder()
                .paymentId(paymentId)
                .payType(payType)
                .amount(amount)
                .cardCompany(issuerCode)
                .cardNumber(cardNumber)
                .orderId(orderId)
                .orderName(orderName)
                .userName(userName)
                .userEmail(userEmail)
                .paymentKey(paymentKey)
                .paySuccessYn(paySuccessYn)
                .payFailReason(payFailReason)
                .createDate(createDate)
                .build();
    }
}
