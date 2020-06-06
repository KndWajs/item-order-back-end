package pl.konrad_wajs.order_items.base;

import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.enums.ItemColor;
import pl.konrad_wajs.order_items.enums.ItemSize;
import pl.konrad_wajs.order_items.persistence.entities.Item;

public class ItemGenerator {

    public static Item getSampleItemEntity() {
        return Item.builder()
                .size(ItemSize.M)
                .color(ItemColor.BLUE)
                .build();
    }

    public static ItemDTO getSampleItemDTO() {
        return ItemDTO.builder()
                .size(ItemSize.M)
                .color(ItemColor.BLUE)
                .build();
    }
}
