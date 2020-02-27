package denis.lishchuk.pets.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    @NotNull
    private String login;
    @NotBlank@NotNull
    private String password;

    private boolean check;

    private Long shelterId;
}
