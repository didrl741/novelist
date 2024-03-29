package toyproject.novelist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.domain.word.Words;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.WordsService;

import java.time.LocalDate;

import static java.lang.Thread.sleep;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {

    private final TodayWordsService todayWordsService;
    private final WordsService wordsService;

//    @Scheduled(fixedRate = 3000)
//    public void testMethod() {
//        log.info("3초마다 실행!");
//    }

    // 매일 정각에 TodayWords 자동으로 수정. 핵심 로직
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void wordChange(){

        todayWordsService.changeWords(wordsService.findWords().makeRandomWords(), LocalDate.now());
    }

}
