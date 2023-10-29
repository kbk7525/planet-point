package project.backend.dto;

import lombok.*;

//기부하기 탭에서 사용하는 dto
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
