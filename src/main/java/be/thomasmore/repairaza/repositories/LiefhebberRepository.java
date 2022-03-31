package be.thomasmore.repairaza.repositories;

import be.thomasmore.repairaza.model.Liefhebber;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LiefhebberRepository extends CrudRepository <Liefhebber, Integer> {
    Optional<Liefhebber> findByUserUsername(String name);
}
