package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.OauthId;
import project.backend.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthId(OauthId oauthId);
}
