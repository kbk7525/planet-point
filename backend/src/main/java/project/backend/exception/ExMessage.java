package project.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExMessage {
    PAYMENT_ERROR_ORDER_NOTFOUND("해당 결제 내역을 조회할 수 없습니다."),
    DB_ERROR_SAVE("데이터 저장에 실패했습니다."),
    PAYMENT_ERROR_ORDER_AMOUNT("결제 금액이 잘못되었습니다."),
    PAYMENT_ERROR_ORDER("결제 관련 오류가 발생했습니다.");

    private final String message;
}
