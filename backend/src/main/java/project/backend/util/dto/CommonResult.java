package project.backend.util.dto;

import lombok.Getter;
import lombok.Setter;
//성공결과 코드와 메시지를 담은 클래스
@Getter
@Setter
public class CommonResult {
    private boolean success;
    private int code;
    private String message;
}