package toyproject.novelist.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class Scheduler {

    @Scheduled(fixedRate = 3000)
    public void testMethod() {
        log.info("3초마다 실행!");
    }
}
