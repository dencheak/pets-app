package denis.lishchuk.pets.repository;

import denis.lishchuk.pets.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

}
