package denis.lishchuk.pets.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
public class PaginationRequest {
    private Integer page;
    private Integer size;
    private SortRequest sortRequest;

    public Pageable toPageRequest(){
        if(sortRequest==null) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(page, size, sortRequest.getDirection(), sortRequest.getField());
    }
}
