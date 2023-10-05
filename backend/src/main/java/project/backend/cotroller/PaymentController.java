package project.backend.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.backend.dto.PaymentReqDTO;
import project.backend.dto.PaymentResDTO;
import project.backend.dto.PaymentResHandleDTO;
import project.backend.exception.BusinessException;
import project.backend.service.PaymentService;
import project.backend.util.dto.SingleResult;
import project.backend.util.service.ResponseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final ResponseService responseService;

    @PostMapping
    public SingleResult<PaymentResDTO> requestPayments(@ModelAttribute PaymentReqDTO paymentReqDTO) {
        try {
            return responseService.getSingleResult(paymentService.reqPayment(paymentReqDTO));
        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

//    @GetMapping("/success")
//    public SingleResult<PaymentResHandleDTO> requestFinalPayment() {
//
//    }
}
