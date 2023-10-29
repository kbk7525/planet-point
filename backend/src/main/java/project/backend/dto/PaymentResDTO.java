package project.backend.dto;

import lombok.*;

//토스쪽에서 보내준 데이터를 사용할 때 필요한 dto
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResDTO {

    private String payType; //지불방법
    private Long amount; //금액
    private String orderId; //주문 id
    private String orderName; //상품이름
    private String userName; //구매자 이름
    private String userEmail; //구매자 이메일
    private String successUrl; //성공 콜백 주소
    private String failUrl; //실패 콜백 주소
    private String createDate; //결제 날짜
    private String paySuccessYn; //결제 성공여부

}
