package toyproject.novelist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.TodayWords;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodayWordsServiceTest {

    @Autowired
    TodayWordsService todayWordsService;
    @Autowired
    WordsService wordsService;

    @Test
    public void TodayWords조회테스트() throws Exception {
        //given
        TodayWords todayWords = todayWordsService.findTodayWords();

        //when
        System.out.println("word =====" + todayWords.toString());

        //then
    }

    @Test
    public void TodayWords변경테스트() throws Exception {
        //given
        TodayWords todayWords = todayWordsService.findTodayWords();

        //when
        String words1 = todayWords.toString();
        todayWordsService.changeWords(wordsService.findWords().makeRandomWords(), LocalDate.now());
        //then
        System.out.println("변경전 =====" + words1);
        System.out.println("변경후 =====" + todayWords.toString());
    }

}