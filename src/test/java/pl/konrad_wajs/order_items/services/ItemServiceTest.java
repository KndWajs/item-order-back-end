package pl.konrad_wajs.item_items.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.konrad_wajs.order_items.base.AbstractIntegrationTestBase;
import pl.konrad_wajs.order_items.base.StoreElementGenerator;
import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.persistence.entities.StoreElement;
import pl.konrad_wajs.order_items.services.ItemService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

@Transactional
public class ItemServiceTest extends AbstractIntegrationTestBase {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private ItemService itemService;

    @Test
    public void shouldReturnOneWhenCheckItemAvailability() {
        // given
        StoreElement storeElement = StoreElementGenerator.getSampleItemEntity();
        ItemDTO itemDTO = ItemDTO.builder().size(storeElement.getSize()).color(storeElement.getColor()).build();
        entityManager.persist(storeElement);

        // when
        int amountOfItems = this.itemService.checkItemAvailability(itemDTO);

        // then
        assertEquals(storeElement.getAmount(), amountOfItems);
    }
}