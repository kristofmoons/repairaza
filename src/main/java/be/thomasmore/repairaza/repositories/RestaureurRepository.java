package be.thomasmore.repairaza.repositories;

import be.thomasmore.repairaza.model.Restaureur;
import org.springframework.data.repository.CrudRepository;

public interface RestaureurRepository extends CrudRepository<Restaureur, Integer> {
}
