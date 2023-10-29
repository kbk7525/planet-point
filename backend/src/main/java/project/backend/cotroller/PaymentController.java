package project.backend.cotroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.backend.dto.*;
import project.backend.exception.BusinessException;
import project.backend.service.PaymentService;
import project.backend.util.dto.ListResult;
import project.backend.util.dto.SingleResult;
import project.backend.util.service.ResponseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final ResponseService responseService;

    //프론트에서 결제하기 버튼을 눌렀을 때 실행
    @PostMapping
    public SingleResult<PaymentResDTO> requestPayments(@ModelAttribute PaymentReqDTO paymentReqDTO) {
        try {
            return responseService.getSingleResult(paymentService.reqPayment(paymentReqDTO));
        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    //최종 결제 성공 api
    @GetMapping("/success")
    public SingleResult<PaymentResHandleDTO> requestFinalPayment(
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Long amount
    ) {
        try {

            paymentService.verifyRequest(paymentKey, orderId, amount);
            PaymentResHandleDTO result = paymentService.requestFinalPayment(paymentKey, orderId, amount);
            return responseService.getSingleResult(result);
        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    //결제 실패 api
    @GetMapping("/fail")
    public SingleResult<PaymentResHandleFailDTO> requestFail(
            @RequestParam(name="code") String errorCode,
            @RequestParam(name="message") String errorMsg,
            @RequestParam(name="orderId") String orderId
    ) {
        try {
            return responseService.getSingleResult(
                    paymentService.requestFail(errorCode, errorMsg, orderId)
            );
        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    //결제 내역을 모두 보여주는 api
    @GetMapping("/all")
    public ListResult<PaymentDTO> getAllPayments(@RequestParam String email) {
        try {
            return responseService.getListResult(paymentService.getAllPayments(email));
        } catch (Exception e) {
            e.getStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }
}