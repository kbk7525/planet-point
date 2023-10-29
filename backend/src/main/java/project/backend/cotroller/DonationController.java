package project.backend.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.dto.DonationDTO;
import project.backend.service.DonationService;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;

    //이미지를 클릭했을 때 donation 테이블의 씨앗 증가하는 api
    @PostMapping("/increase")
    public ResponseEntity<String> increase(@RequestParam Long elementId, @RequestParam int cnt) {
        try{
            donationService.increaseElementSeed(elementId, cnt);
            return ResponseEntity.ok("씨앗이 정상 기부 되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("기부에 실패했습니다.");
        }
    }

    //기부하기 탭의 이미지를 누르면 User 테이블의 씨앗 감소하는 api
    @PostMapping("/decrease")
    public ResponseEntity<String> decrease(@RequestParam String email, @RequestParam int cnt) {
        try {
            donationService.decreaseUserSeed(email, cnt);
            return ResponseEntity.ok("씨앗이 성공적으로 감소되었습니다.");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("씨앗을 감소시키지 못했습니다.");
        }
    }

    //기부하기 페이지 요소의 현재 씨앗값 뿌려 주는 api
    @GetMapping("/print/{elementId}")
    public ResponseEntity<DonationDTO> print(@PathVariable Long elementId) {
        DonationDTO donationDTO = donationService.findByElementId(elementId);
        if(donationDTO != null) {
            return ResponseEntity.ok(donationDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}