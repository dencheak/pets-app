package denis.lishchuk.pets.dto.response;

import denis.lishchuk.pets.entity.Pet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PetResponse {

    private Long id;

    private String name;

    private Long kindId;

    private Long addressId;

    //private String imagePath;

    public PetResponse(Pet pet) {
        id = pet.getId();
        name = pet.getName();
        kindId = pet.getKind().getId();
        addressId = pet.getAddress().getId();
        //imagePath = pet.getImagePath();
    }
}
