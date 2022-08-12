package toyproject.novelist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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


}
