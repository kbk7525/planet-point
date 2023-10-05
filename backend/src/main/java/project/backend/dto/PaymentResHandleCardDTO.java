package project.backend.dto;

import lombok.Data;

@Data
public class PaymentResHandleCardDTO {
    String cardCompany;
    String cardNumber;
    String approveNo;
    String cardType;
    String ownerType;
    String acquireStatus;
}
