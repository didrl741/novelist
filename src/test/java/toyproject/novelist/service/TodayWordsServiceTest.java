package toyproject.novelist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.TodayWords;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodayWordsServiceTest {

    @Autowired
    TodayWordsService todayWordsService;

    @Test
    public void TodayWords조회테스트() throws Exception {
        //given
        TodayWords todayWords = todayWordsService.findTodayWords();

        //when
        System.out.println("word =====" + todayWords.toString());

        //then
    }

}