package project.backend.dto;

import lombok.Data;

//토스쪽에서 카드결제를 포함한 모든 결제성공시 넘어오는 데이터를 사용하기 위한 dto
@Data
public class PaymentResHandleDTO {
    String mId; //가맹점 id
    String version; //payment 객체 응답 버전
    String paymentKey;
    String orderId;
    String orderName;
    String method; //결제 수단
    String status; //결제 처리 상태
    String requestedAt; //결제 요청 시간
    String approvedAt; //결제 승인 시간
    String type; //결제 타입 정보
    PaymentResHandleCardDTO card; //카드 결제
}
