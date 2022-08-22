package toyproject.novelist.domain.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor @NoArgsConstructor
public class TodayWordsEmbedded {

    private String word1;
    private String word2;
    private String word3;
    private String word4;
    private String word5;

    @Builder
    public TodayWordsEmbedded(TodayWords todayWords) {

        String[] words = todayWords.makeArr();

        this.word1 = words[0];
        this.word2 = words[1];
        this.word3 = words[2];
        this.word4 = words[3];
        this.word5 = words[4];
    }
}
