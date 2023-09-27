package project.backend.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.Payment;
import project.backend.dto.PaymentReqDTO;
import project.backend.dto.PaymentResDTO;
import project.backend.repository.PaymentRepository;
import project.backend.repository.UserRepository;

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

                    )
        }
    }
}
