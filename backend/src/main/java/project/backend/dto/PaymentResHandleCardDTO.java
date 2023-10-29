package project.backend.dto;

import lombok.Data;

//카드결제시 토스쪽에서 보내준 데이터를 사용할 때 필요한 dto
@Data
public class PaymentResHandleCardDTO {
    String issuerCode; //카드 발급사 코드
    String number; //카드 번호
    String approveNo; //승인번호
    String cardType; //카드 타입(체크, 신용..)
    String ownerType; //개인 ,법인
    String acquireStatus;
}
