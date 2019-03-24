package denis.lishchuk.pets.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequest {
    @NotBlank@NotNull
    private String country;

    @NotBlank@NotNull
    private String city;

    @NotBlank@NotNull
    private String street;

    @NotBlank@NotNull
    private String numberOfHouse;
}
