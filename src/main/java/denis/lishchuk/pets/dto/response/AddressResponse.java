package denis.lishchuk.pets.dto.response;

import denis.lishchuk.pets.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;

    private String country;

    private String city;

    private String street;

    private String numberOfHouse;

    public AddressResponse(Address address){
        id = address.getId();
        country = address.getCountry();
        city = address.getCity();
        street = address.getStreet();
        numberOfHouse = address.getNumberOfHouse();

    }
}
