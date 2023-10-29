package project.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//결제 실패시 필요한 dto
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResHandleFailDTO {
    String errorCode;
    String errorMsg;
    String orderId;
}
