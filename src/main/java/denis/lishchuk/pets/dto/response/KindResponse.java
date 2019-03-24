package denis.lishchuk.pets.dto.response;

import denis.lishchuk.pets.entity.Kind;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KindResponse {

    private Long id;

    private String name;

    private String breed;

    public KindResponse(Kind kind){
        id = kind.getId();
        name = kind.getName();
        breed = kind.getBreed();
    }
}
