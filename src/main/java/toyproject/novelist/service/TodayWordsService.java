package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.repository.TodayWordsRepository;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodayWordsService {

    private final TodayWordsRepository todayWordsRepository;

    @Transactional
    public Long save(TodayWords todayWords) {
        todayWordsRepository.save(todayWords);
        return todayWords.getId();
    }

    public TodayWords findTodayWords() {
        return todayWordsRepository.findTodayWords();
    }

    @Transactional
    public void changeWords(String[] wordArr, LocalDate localDate) {
        todayWordsRepository.changeWords(wordArr, localDate);
    }
}
