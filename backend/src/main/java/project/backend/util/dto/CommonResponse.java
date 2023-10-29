package project.backend.util.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

//결제성공, 실패에 대한 메시지를 담은 두ㅕㅡ
@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(1, "성공"),
    FAIL(-1, "실패");

    private int code;
    private String message;
}
