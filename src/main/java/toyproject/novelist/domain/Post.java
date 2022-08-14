package toyproject.novelist.domain;


import lombok.Getter;
import lombok.Setter;

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
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Love> loves = new ArrayList<Love>();

    //== 연관관계 편의 메서드 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }
}
