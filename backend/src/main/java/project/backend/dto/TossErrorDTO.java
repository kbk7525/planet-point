package project.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TossErrorDTO {
    String code;
    String message;
}
