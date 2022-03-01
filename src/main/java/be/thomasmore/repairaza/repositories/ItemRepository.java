package be.thomasmore.repairaza.repositories;

import be.thomasmore.repairaza.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    Iterable<Item> findBySoortObjectEquals(String soortObject);


}
