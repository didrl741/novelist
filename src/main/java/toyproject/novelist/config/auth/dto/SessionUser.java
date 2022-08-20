package toyproject.novelist.config.auth.dto;

import lombok.Getter;
import lombok.ToString;
import toyproject.novelist.domain.user.User;

import java.io.Serializable;

@Getter
@ToString
public class SessionUser implements Serializable {

    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
