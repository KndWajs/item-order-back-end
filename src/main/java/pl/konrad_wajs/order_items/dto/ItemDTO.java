package pl.konrad_wajs.order_items.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.konrad_wajs.order_items.enums.ItemColor;
import pl.konrad_wajs.order_items.enums.ItemSize;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    private ItemSize size;

    private ItemColor color;
}
