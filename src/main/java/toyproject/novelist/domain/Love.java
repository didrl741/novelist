package toyproject.novelist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toyproject.novelist.domain.user.User;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Love {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "love_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Love(User user, Post post) {
        this.user = user;
        this.post = post;

        // 주의!
        post.getLoves().add(this);
    }

    //== 연관관계 편의 메서드 ==//
    public void setUser(User user) {
        this.user = user;
        // Member에 List<Love> 있는경우 add
    }

    //== 연관관계 편의 메서드 ==//
    public void setPost(Post post) {
        this.post = post;
        post.getLoves().add(this);
    }
}
