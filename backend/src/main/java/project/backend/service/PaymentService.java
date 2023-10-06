package project.backend.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import net.minidev.json.JSONObject;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import project.backend.domain.Payment;
import project.backend.dto.*;
import project.backend.exception.BusinessException;
import project.backend.exception.ExMessage;
import project.backend.repository.PaymentRepository;
import project.backend.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Value("${payment.toss.test_client_key}")
    private String testClientKey;

    @Value("${payment.toss.test_secret_key}")
    private String testSecretKey;

    @Value("${payment.toss.success_url}")
    private String successUrl;

    @Value("${payment.toss.fail_url}")
    private String failUrl;

    @Value("${payment.toss.origin_url}")
    private String tossOriginUrl;

    @Transactional
    public PaymentResDTO reqPayment(PaymentReqDTO paymentReqDTO) {
        Long amount = paymentReqDTO.getAmount();
        String payType = paymentReqDTO.getPayType();
        String userEmail = paymentReqDTO.getUserEmail();
        String orderName = paymentReqDTO.getOrderName();
        PaymentResDTO paymentResDTO;
        try {
            Payment payment = paymentReqDTO.toEntity();
            userRepository.findByEmail(userEmail)
                    .ifPresentOrElse(
                            M-> M.addPayment(payment)
                            , () -> {
                                throw new BusinessException(ExMessage.PAYMENT_ERROR_ORDER_NOTFOUND);
                            });
            paymentResDTO = payment.toRes();
            paymentResDTO.setSuccessUrl(successUrl);
            paymentResDTO.setFailUrl(failUrl);
            return paymentResDTO;
        } catch(Exception e) {
            throw new BusinessException(ExMessage.DB_ERROR_SAVE);
        }
    }

    //결제 요청 증명
    @Transactional
    public void verifyRequest(String paymentKey, String orderId, Long amount) {
        paymentRepository.findByOrderId(orderId)
                .ifPresentOrElse(
                        P -> {
                            if(P.getAmount().equals(amount)) {
                                P.setPaymentKey(paymentKey);
                            }
                            else {
                                throw new BusinessException(ExMessage.PAYMENT_ERROR_ORDER_AMOUNT);
                            }
                        }, () -> {
                            throw new BusinessException(ExMessage.PAYMENT_ERROR_ORDER);
                        }
                );
    }

    @Transactional
    public PaymentResHandleDTO requestFinalPayment(String paymentKey, String orderId, Long amount) {
        Payment pay = paymentRepository.findByPaymentKey(paymentKey)
                .orElseThrow(() -> new BusinessException((ExMessage.PAYMENT_ERROR_ORDER_NOTFOUND)));
        String payType = "카드";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String encodedAuth = new String(Base64.getEncoder().encode((testSecretKey + ":").getBytes(StandardCharsets.UTF_8)));
        headers.setBasicAuth(encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        JSONObject param = new JSONObject();
        param.put("orderId", orderId);
        param.put("amount", amount);
        PaymentResHandleDTO payResDTO;
        try {
            payResDTO = rest.postForEntity(
                 tossOriginUrl + "/payments/" + paymentKey,
                    new HttpEntity<>(param, headers),
                    PaymentResHandleDTO.class
            ).getBody();
            if(payResDTO == null) {
                throw new BusinessException(ExMessage.PAYMENT_ERROR_ORDER);
            }
        } catch (Exception e) {
            String errorResponse = e.getMessage().split(": ")[1];
            String errorMessage = new Gson()
                    .fromJson(
                            errorResponse.substring(1, errorResponse.length()-1),
                            TossErrorDTO.class
                    ).getMessage();
            throw new BusinessException(errorMessage);
        }
        if(payType.equals("카드")) {
            PaymentResHandleCardDTO card = payResDTO.getCard();
            paymentRepository.findByOrderId(payResDTO.getOrderId())
                    .ifPresent(payment -> {
                        payment.setCardCompany(card.getCardCompany());
                        payment.setCardNumber(card.getCardNumber());
                        payment.setPaySuccessYn("Y");
                    });
        }
        return payResDTO;
    }

    @Transactional
    public PaymentResHandleFailDTO requestFail(String errorCode, String errorMsg, String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException(ExMessage.PAYMENT_ERROR_ORDER_NOTFOUND));
        payment.setPaySuccessYn("N");
        payment.setPayFailReason(errorMsg);
        return PaymentResHandleFailDTO.builder()
                .orderId(orderId)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    @Transactional(readOnly = true)
    public List<PaymentDTO> getAllPayments(String userEmail) {
        String email = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new BusinessException(ExMessage.MEMBER_ERROR_NOT_FOUND))
                .getEmail();
        return paymentRepository.findAllByUserEmail(email)
                .stream().map(Payment::toDTO)
                .collect(Collectors.toList());
    }
}
