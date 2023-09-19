package project.backend.cotroller;

import org.springframework.core.convert.converter.Converter;
import project.backend.domain.OauthServerType;

public class OauthServerTypeConverter implements Converter<String, OauthServerType> {

    @Override
    public OauthServerType convert(String source) {
        return OauthServerType.fromName(source);
    }
}
