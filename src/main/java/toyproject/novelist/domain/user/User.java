package toyproject.novelist.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import toyproject.novelist.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    private String auth_email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    @Builder
    public User(String name, String email, Role role, String password, String auth_email) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = enCodePW(password);
        this.auth_email = auth_email;
    }

    public User update(String name) {
        this.name = name;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public String enCodePW(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.encode(password);
    }

    public void changePW(String password) {

        this.password = enCodePW(password);
    }

    public void changeAuth_Mail(String auth_email) {

        this.auth_email = auth_email;
    }
}
