package toyproject.novelist.domain.word;

import lombok.AllArgsConstructor;
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
}
