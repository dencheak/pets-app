package denis.lishchuk.pets.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class PetRequest {
    private String name;
    @NotNull
    private Long kindId;
    @NotNull
    private Long addressId;

    private FileRequest fileRequest;
}
