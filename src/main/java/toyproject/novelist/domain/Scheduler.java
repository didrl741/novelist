package toyproject.novelist.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.service.TodayWordsService;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {

    private final TodayWordsService todayWordsService;

//    @Scheduled(fixedRate = 3000)
//    public void testMethod() {
//        log.info("3초마다 실행!");
//    }

    @Scheduled(cron = "0 0 0 * * *")
    public void testMethod2(){
        log.info("매일 00시 정각");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void wordChange(){

        TodayWords todayWords = todayWordsService.findTodayWords();

        todayWordsService.changeWords(todayWords.getId());
    }
}
