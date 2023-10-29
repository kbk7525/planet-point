package project.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//토스쪽에서 오류가 났을 때 보내는 메시지를 사용할 때 필요한 dto
@Data
@AllArgsConstructor
public class TossErrorDTO {
    String code;
    String message;
}
