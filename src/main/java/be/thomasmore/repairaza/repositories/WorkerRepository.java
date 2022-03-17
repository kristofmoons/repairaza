package be.thomasmore.repairaza.repositories;

import be.thomasmore.repairaza.model.worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<worker, Integer> {
}
