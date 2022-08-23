package toyproject.novelist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "비밀번호 필수입니다.")
    private String password;

    private String auth_email;
}
