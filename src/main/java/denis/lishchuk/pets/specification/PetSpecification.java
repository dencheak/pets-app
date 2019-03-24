package denis.lishchuk.pets.specification;

import denis.lishchuk.pets.dto.request.PetFilterRequest;
import denis.lishchuk.pets.entity.Kind;
import denis.lishchuk.pets.entity.Pet;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class PetSpecification implements Specification<Pet> {

    private PetFilterRequest filter;

    public PetSpecification(PetFilterRequest filter){
        this.filter= filter;
    }

    @Override
    public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate byNameLike = findByNameLike(root, criteriaBuilder);
        if(byNameLike != null) predicates.add(byNameLike);

        Predicate byKind = findByKind(root, criteriaBuilder);
        if(byKind != null) predicates.add(byKind);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByKind(Root<Pet>root, CriteriaBuilder criteriaBuilder){
        Join<Pet, Kind> kindJoin = root.join("kind");
        return kindJoin.get("id").in(filter.getKindsId().toArray());
    }

    private Predicate findByNameLike(Root<Pet> root, CriteriaBuilder criteriaBuilder){
        String name = filter.getName();
        if(name ==null || name.trim().isEmpty()){
            return null;
        }
        return criteriaBuilder.like(root.get("name"), '%' + name + '%');
    }
}
