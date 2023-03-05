package toyproject.novelist.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import toyproject.novelist.domain.user.Member;
import toyproject.novelist.domain.word.TodayWords;
import toyproject.novelist.domain.word.TodayWordsEmbedded;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String content;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Love> loves = new ArrayList<Love>();

    private int loveCount;

    @Transient
    private boolean isLovedByLogInedUser;

    @Embedded
    private TodayWordsEmbedded todayWordsEmbedded;

    //== 연관관계 편의 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public void changeLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public void setLovedByLogInedUser(boolean lovedByLogInedUser) {
        this.isLovedByLogInedUser = lovedByLogInedUser;
    }

    @Builder
    public Post(String content, LocalDateTime postDate, Member member, TodayWords todayWords) {
        this.content = content;
        this.postDate = postDate;
        this.member = member;
        this.loveCount = 0;

        this.todayWordsEmbedded = new TodayWordsEmbedded(todayWords);

    }

    @Builder
    public Post(String content, LocalDateTime postDate, TodayWords todayWords) {
        this.content = content;
        this.postDate = postDate;
        this.loveCount = 0;

        this.todayWordsEmbedded = new TodayWordsEmbedded(todayWords);
    }
}
