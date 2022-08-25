package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.repository.TodayWordsRepository;

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

}
