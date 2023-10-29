package project.backend.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project.backend.domain.User;
import project.backend.dto.UserDTO;
import project.backend.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.json.simple.parser.JSONParser;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입시 새로운 user에 대한 정보들을 user 데이터베이스에 저장하는 메소드
    public User createUser(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findByEmail(userDTO.getEmail());
        if(existUser.isEmpty()) {
            User newUser = User.builder()
                    .id(userDTO.getId())
                    .uid(userDTO.getUid())
                    .email(userDTO.getEmail())
                    .name(userDTO.getName())
                    .mobile(userDTO.getMobile())
                    .seed(10)
                    .build();
            return userRepository.save(newUser);
        }
        else {
            return null;
        }
    }

    //자장된 user 정보를 반환하는 메소드
    public Optional<User> userInfo(String email) {
        return userRepository.findByEmail(email);
    }

    //받은 토큰을 풀어서 이메일을 반환하는 메소드
    public String getUserInfoToken(String accessToken) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        //네이버 토큰을 이용해 사용자의 정보를 json형식으로 받아옴
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObj.get("response");

        return String.valueOf(account.get("email"));
    }

    //user의 씨앗을 늘려주는 메소드
    public void increaseSeed(String email, int cnt) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new RuntimeException("해당 user가 없습니다.");
        }
        User existUser = user.get();
        int currentSeed = existUser.getSeed();
        existUser.setSeed(currentSeed+cnt);
        userRepository.save(existUser);
    }
}