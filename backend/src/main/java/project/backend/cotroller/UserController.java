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

    @PostMapping("/token")
    public ResponseEntity<String> emailInfo(@RequestBody String token) throws Exception {
        String userInfoToken = userService.getUserInfoToken(token);
        return ResponseEntity.ok(userInfoToken);
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> loginInfo(@RequestParam String email) {
        Optional<User> user = userService.userInfo(email);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
