package toyproject.novelist.domain;

import lombok.Getter;
import lombok.Setter;
import toyproject.novelist.domain.user.User;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Love {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "love_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //== 연관관계 편의 메서드 ==//
    public void setUser(User member) {
        this.member = member;
        // Member에 List<Love> 있는경우 add
    }

    //== 연관관계 편의 메서드 ==//
    public void setPost(Post post) {
        this.post = post;
        post.getLoves().add(this);
    }
}
