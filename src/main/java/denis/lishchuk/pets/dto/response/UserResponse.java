package denis.lishchuk.pets.dto.response;

import denis.lishchuk.pets.entity.Role;
import denis.lishchuk.pets.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String login;

    private Long shelterId;

    private Role role;

    public UserResponse(User user){
        id = user.getId();
        login = user.getLogin();
        if(user.getShelter()!=null) {
            shelterId = user.getShelter().getId();
        }
        role = user.getRole();
    }
}
