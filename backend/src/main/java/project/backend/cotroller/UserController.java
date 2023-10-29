package project.backend.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.User;
import project.backend.dto.UserDTO;
import project.backend.service.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    //네이버에서 가져온 정보로 회원가입하는 api
    @PostMapping("/save")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        if(createdUser != null) {
            return ResponseEntity.ok("회원가입 성공");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("이미 회원가입 된 이메일입니다.");
        }
    }

    //현재 페이지에서 토큰 값을 가져와서 이메일값을 반환하는 api
    @PostMapping("/token")
    public ResponseEntity<String> emailInfo(@RequestBody String token) throws Exception {
        String userInfoToken = userService.getUserInfoToken(token);
        return ResponseEntity.ok(userInfoToken);
    }

    //user 정보를 프론트에게 넘겨주는 api
    @GetMapping("/info")
    public ResponseEntity<?> loginInfo(@RequestParam String email) {
        Optional<User> user = userService.userInfo(email);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //user의 씨앗값을 늘려주는 api
    @PostMapping("/increaseSeed")
    public ResponseEntity<String> increaseSeed(@RequestParam String email,
                                               @RequestParam int cnt) {
        try{
            userService.increaseSeed(email, cnt);
            return ResponseEntity.ok("씨앗이 증가했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("씨앗이 증가하지 못했습니다.");
        }
    }
}
