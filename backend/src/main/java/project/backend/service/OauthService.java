package project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.domain.OauthServerType;
import project.backend.domain.User;
import project.backend.domain.authcode.AuthCodeRequestUrlProviderComposite;
import project.backend.domain.client.OauthMemberClientComposite;
import project.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final UserRepository userRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public Long login(OauthServerType oauthServerType, String authcode) {
        User user = oauthMemberClientComposite.fetch(oauthServerType, authcode);
        User saved = userRepository.findByOauthId(user.oauthId())
                .orElseGet(()-> userRepository.save(user));
        return saved.id();
    }
}
