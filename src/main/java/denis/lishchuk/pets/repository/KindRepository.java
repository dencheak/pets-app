package denis.lishchuk.pets.repository;

import denis.lishchuk.pets.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepository extends JpaRepository<Kind, Long> {
    Kind findByName(String name);
}
