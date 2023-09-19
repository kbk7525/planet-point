package project.backend.domain.authcode;

import project.backend.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    //어떤 serverType을 지원하는지 보여주는 메소드
    OauthServerType supportServer();

    //url을 생성해서 반환
    String provide();
}
