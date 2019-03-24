package denis.lishchuk.pets.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KindRequest {
    @NotBlank
    @NotNull
    private String name;

    private String breed;
}
