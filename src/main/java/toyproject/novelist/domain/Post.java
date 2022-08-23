package toyproject.novelist.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toyproject.novelist.domain.user.User;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.domain.word.TodayWordsEmbedded;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
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

    private int loveCount;

    @Transient
    private boolean isLovedByLogInedUser;

    @Embedded
    private TodayWordsEmbedded todayWordsEmbedded;

    //== 연관관계 편의 메서드 ==//
    public void setUser(User user) {
        this.user = user;
//        user.getPosts().add(this); 필요할 시 추가
    }

    @Builder
    public Post(String content, LocalDateTime postDate, User user, TodayWords todayWords) {
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.loveCount = 0;

        this.todayWordsEmbedded = new TodayWordsEmbedded(todayWords);

    }
}
