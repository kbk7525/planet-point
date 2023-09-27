package project.backend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String uid;
    private String name;
    private String email;
    private String mobile;
    private int seed;

}