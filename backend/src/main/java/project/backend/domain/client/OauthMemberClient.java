package project.backend.domain.client;

import project.backend.domain.OauthServerType;
import project.backend.domain.User;

public interface OauthMemberClient {

    OauthServerType supportServer();

    User fetch(String code);
;}
