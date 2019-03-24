package denis.lishchuk.pets.dto.response;

import denis.lishchuk.pets.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String login;

    private String password;

    private Long shelterId;

    public UserResponse(User user){
        id = user.getId();
        login = user.getLogin();
        password = user.getPassword();
        shelterId = user.getShelter().getId();
    }
}
