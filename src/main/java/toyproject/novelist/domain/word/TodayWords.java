package toyproject.novelist.domain.word;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class TodayWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "today_words_id")
    private Long id;

    private String word1;
    private String word2;
    private String word3;
    private String word4;
    private String word5;

    private LocalDate localDate;

    public TodayWords(String[] wordArr, LocalDate localDate) {
        this.word1 = wordArr[0];
        this.word2 = wordArr[1];
        this.word3 = wordArr[2];
        this.word4 = wordArr[3];
        this.word5 = wordArr[4];

        this.localDate = localDate;
    }
}
