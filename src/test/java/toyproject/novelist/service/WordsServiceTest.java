package toyproject.novelist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import toyproject.novelist.domain.word.Words;

@SpringBootTest
@Transactional
class WordsServiceTest {

    @Autowired WordsService wordsService;

    @Test
    @Rollback(value = false)
    public void 단어등록() throws Exception {
        //given
        Words words = new Words();
        words.getWordCollection().add("사과");
        words.getWordCollection().add("바나나");
        words.getWordCollection().add("우유");
        words.getWordCollection().add("과자");
        words.getWordCollection().add("사탕");

        //when
        wordsService.join(words);

        //then
        System.out.println("words.getWordCollection() ==== > " + words.getWordCollection());
    }

}