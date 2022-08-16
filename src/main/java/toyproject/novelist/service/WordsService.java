package toyproject.novelist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.Words;
import toyproject.novelist.repository.WordsRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordsService {
     private final WordsRepository wordsRepository;

    @Transactional
    public Long join(Words words) {
        wordsRepository.save(words);
        return words.getId();
    }

    @Transactional
    public void addWord(String word) {
        wordsRepository.addWord(word);
    }
}
