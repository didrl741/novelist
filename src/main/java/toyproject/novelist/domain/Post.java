package toyproject.novelist.domain;


import lombok.Getter;
import lombok.Setter;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.domain.word.TodayWordsEmbedded;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String content;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Love> loves = new ArrayList<Love>();

    private int loveCount = 0;

    @Embedded
    private TodayWordsEmbedded todayWordsEmbedded;

    //== 연관관계 편의 메서드 ==//
    public void setUser(User user) {
        this.user = user;
//        user.getPosts().add(this); 필요할 시 추가
    }
}
