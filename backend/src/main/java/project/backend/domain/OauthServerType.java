package project.backend.domain;
import static java.util.Locale.ENGLISH;

public enum OauthServerType {

    NAVER,
    ;
    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(ENGLISH));
    }

}
