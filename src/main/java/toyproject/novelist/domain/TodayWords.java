package toyproject.novelist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodayWords {

    private String word1;
    private String word2;
    private String word3;
    private String word4;
    private String word5;

}
