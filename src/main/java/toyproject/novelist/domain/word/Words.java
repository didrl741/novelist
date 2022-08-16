package toyproject.novelist.domain.word;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "words_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "word_collection", joinColumns = @JoinColumn(name = "wrods_id"))
    @Column(name = "word")
    private List<String> wordCollection = new ArrayList<>();

    // 단어 추가 메소드
    public void addWord(String word) {
        this.getWordCollection().add(word);
    }

    // 랜덤으로 5단어 생성하여 배열로 반환
    public String[] makeRandomWords() {
        String[] wordFive = new String[5];
        Random random = new Random();

        Set<Integer> ranNumSet = new HashSet<>();

        while (ranNumSet.size() <= 5) {
            int index = random.nextInt( this.getWordCollection().size( ) );
            ranNumSet.add((Integer) index);
        }

        Integer[] ranNumArr = ranNumSet.stream().toArray(n -> new Integer[n]);

        for (int i = 0; i < 5; i++) {
            wordFive[i] = this.getWordCollection().get(ranNumArr[i]);
        }

        return wordFive;
    }
}
