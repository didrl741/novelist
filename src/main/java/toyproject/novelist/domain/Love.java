package toyproject.novelist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toyproject.novelist.domain.user.Member;

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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Love(Member member, Post post) {
        this.member = member;
        this.post = post;

        // 주의!
        post.getLoves().add(this);
    }

    //== 연관관계 편의 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        // Member에 List<Love> 있는경우 add
    }

    //== 연관관계 편의 메서드 ==//
    public void setPost(Post post) {
        this.post = post;
        post.getLoves().add(this);
    }
}
