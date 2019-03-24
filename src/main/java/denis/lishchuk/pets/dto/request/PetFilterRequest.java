package denis.lishchuk.pets.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PetFilterRequest {

    private String name;

    private List<Long> kindsId = new ArrayList<>();

    private PaginationRequest pagination = new PaginationRequest();
}
