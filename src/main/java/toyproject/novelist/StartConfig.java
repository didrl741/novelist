package toyproject.novelist;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.domain.word.Words;
import toyproject.novelist.service.TodayWordsService;
import toyproject.novelist.service.WordsService;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class StartConfig implements CommandLineRunner {

    private final WordsService wordsService;
    private final TodayWordsService todayWordsService;

    // 서버가 시작될 때 생성
    @Override
    public void run(String... args) {

        Words words = new Words();
        for (int i = 0; i <= 20; i++) {
            words.addWord("단어" + i);
        }

        wordsService.join(words);

        // 랜덤으로 5단어 가져와서 TodayWords 엔티티 생성 (중복 문제 해결)
        TodayWords todayWords = new TodayWords(words.makeRandomWords(), LocalDate.now());

        todayWordsService.save(todayWords);

    }
}
