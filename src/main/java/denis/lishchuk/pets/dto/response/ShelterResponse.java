package denis.lishchuk.pets.dto.response;


import denis.lishchuk.pets.entity.Shelter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShelterResponse {
    private Long id;

    private String name;

    private Long userId;

    private Long addressId;

    public ShelterResponse(Shelter shelter){
        id = shelter.getId();
        name = shelter.getName();
        if(shelter.getAdmin()!=null) {
            userId = shelter.getAdmin().getId();
        }
        addressId = shelter.getAddress().getId();
    }
}
