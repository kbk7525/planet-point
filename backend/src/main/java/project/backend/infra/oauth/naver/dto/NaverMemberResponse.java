package project.backend.infra.oauth.naver.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import project.backend.domain.OauthId;
import project.backend.domain.User;

import static project.backend.domain.OauthServerType.NAVER;

@JsonNaming(value = SnakeCaseStrategy.class)
public record NaverMemberResponse(
        String resultCode,
        String message,
        Response response
    ) {
    public User toDomain() {
        return User.builder()
                .oauthId(new OauthId(String.valueOf(response.id), NAVER))
                .userName(response.name)
                .email(response.email)
                .mobile(response.mobile)
                .build();
    }

    public record Response(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profileImage,
            String birthYear,
            String mobile

    ) {

    }
}
