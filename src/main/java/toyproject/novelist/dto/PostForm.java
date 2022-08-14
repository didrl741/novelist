package toyproject.novelist.dto;

import lombok.Getter;
import lombok.Setter;
import toyproject.novelist.annotation.WriteLimit;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PostForm {

    @NotEmpty(message = "내용은 필수입니다.")
    @WriteLimit
    private String content;
}
