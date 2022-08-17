package toyproject.novelist.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject.novelist.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Builder
    public Member(String name, String email, Role role, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Member update(String name) {
        this.name = name;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
