package project.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long elementId; //요소의 아이디

    @Column
    private String donationName; //요소의 이름

    //각 요소의 현재 씨앗값
    @Column
    private int seedCnt;
}
