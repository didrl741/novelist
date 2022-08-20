package toyproject.novelist.dto;

import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;

    private String name;

    private String email;

    protected MemberDto() {

    }

    public MemberDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
