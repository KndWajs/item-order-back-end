package pl.konrad_wajs.order_items.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.konrad_wajs.order_items.enums.ItemColor;
import pl.konrad_wajs.order_items.enums.ItemSize;
import pl.konrad_wajs.order_items.persistence.entities.StoreElement;

@Repository
public interface StoreRepository extends CrudRepository<StoreElement, Long> {

    StoreElement findStoreElementBySizeAndColor(ItemSize size,  ItemColor color);
}

