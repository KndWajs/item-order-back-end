package pl.konrad_wajs.order_items.base;

import pl.konrad_wajs.order_items.enums.ItemColor;
import pl.konrad_wajs.order_items.enums.ItemSize;
import pl.konrad_wajs.order_items.persistence.entities.StoreElement;

public class StoreElementGenerator {

    public static StoreElement getSampleItemEntity() {
        return StoreElement.builder()
                .size(ItemSize.M)
                .color(ItemColor.BLUE)
                .amount(1)
                .build();
    }
}
