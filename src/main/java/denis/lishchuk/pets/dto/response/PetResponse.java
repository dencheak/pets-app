package denis.lishchuk.pets.dto.response;

import denis.lishchuk.pets.entity.Pet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PetResponse {

    private Long id;

    private String name;

    private KindResponse kindResponse;
    private AddressResponse addressResponse;

    private String imagePath;

    public PetResponse(Pet pet) {
        id = pet.getId();
        name = pet.getName();
        kindResponse = new KindResponse(pet.getKind());
        addressResponse = new AddressResponse(pet.getAddress());
        imagePath = pet.getImagePath();
    }
}
