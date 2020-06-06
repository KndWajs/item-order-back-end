package pl.konrad_wajs.order_items.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.konrad_wajs.order_items.persistence.entities.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}

