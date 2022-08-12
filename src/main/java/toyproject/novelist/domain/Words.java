package toyproject.novelist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "words_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "word_collection", joinColumns = @JoinColumn(name = "wrods_id"))                                  // member_id를 FK로.
    @Column(name = "word")
    private Set<String> wordCollection = new HashSet<>();
}
