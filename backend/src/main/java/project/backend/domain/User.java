package project.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "oauth_member",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "oauth_id_unique",
                    columnNames = {
                            "oauth_server_id",
                            "oauth_server"
                    }
            ),
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;
    private String userName;
    private String email;
    private String mobile;

    public Long id() {
        return id;
    }

    public OauthId oauthId() {
        return oauthId;
    }

    public String userName() {
        return userName;
    }

    public String email() {
        return email;
    }

    public String mobile() {
        return mobile;
    }

}
