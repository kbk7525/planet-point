package project.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//토스 결제에 필요한 configure
@Configuration
@Getter
public class PaymentConfig {
    @Value("${payment.toss.test_client_key}")
    private String clientKey;

    @Value("${payment.toss.test_secret_key}")
    private String secretKey;

    @Value("${payment.toss.success_url}")
    private String successUrl;

    @Value("${payment.toss.fail_url}")
    private String failUrl;

    private static final String URL = "https://api.tosspayments.com/v1/payments/";
}
