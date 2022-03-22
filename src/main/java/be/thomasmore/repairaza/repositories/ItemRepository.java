package be.thomasmore.repairaza.repositories;

import be.thomasmore.repairaza.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Query("SELECT i FROM Item i WHERE " +
            "(:minPrice IS NULL OR :minPrice <= i.price) AND " +
            "(:maxPrice IS NULL OR i.price <= :maxPrice) AND " +
            "(:intstock IS NULL OR i.InStock = :intstock) AND " +
            "(:name IS NULL OR  upper(i.itemName)  like upper(concat('%', :name, '%') )) AND " +
            "(:soort IS NULL OR  upper(i.soortObject)  like upper( :soort ))")
    List<Item> findByFilter(@Param("minPrice") Double minPrice,
                            @Param("maxPrice") Double maxPrice,
                            @Param("intstock") Boolean intstock,
                            @Param("name") String Name,
                            @Param("soort") String soort);

}
