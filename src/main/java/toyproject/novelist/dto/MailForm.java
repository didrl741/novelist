package toyproject.novelist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailForm {

    private String address;
    private String title;
    private String message;
}
