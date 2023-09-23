package project.backend.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.domain.User;
import project.backend.dto.UserDTO;
import project.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        if(createdUser != null) {
            return ResponseEntity.ok("로그인 성공");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 회원가입 된 이메일입니다.");
        }
    }
}
