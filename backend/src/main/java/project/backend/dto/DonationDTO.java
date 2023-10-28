package project.backend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationDTO {
    private Long id;
    private Long elementId;
    private String donationName;
    private int seedCnt;
}
