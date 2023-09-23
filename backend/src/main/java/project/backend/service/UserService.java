package project.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.domain.User;
import project.backend.dto.UserDTO;
import project.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User existUser = userRepository.findByEmail(userDTO.getEmail());
        if(existUser == null) {
            User newUser = User.builder()
                    .id(userDTO.getId())
                    .uid(userDTO.getUid())
                    .email(userDTO.getEmail())
                    .name(userDTO.getName())
                    .mobile(userDTO.getMobile())
                    .seed(0)
                    .build();
            return userRepository.save(newUser);
        }
        else {
            return null;
        }
    }
}
