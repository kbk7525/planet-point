package project.backend.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//토스 결제 시각 configure
@Configuration
public class DateConfig {
    public String getNowDate() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
