package project.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import project.backend.dto.PaymentResDTO;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column
    private String payType;

    @Column
    private Long amount;

    @Column
    private String orderId;

    @Column
    private String orderName;

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    private String createDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    public PaymentResDTO toDTO() {
        return PaymentResDTO.builder()
                .payType(payType)
                .amount(amount)
                .orderId(orderId)
                .orderName(orderName)
                .userName(userName)
                .userEmail(userEmail)
                .createDate(createDate)
                .build();
    }
}
