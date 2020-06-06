package pl.konrad_wajs.order_items.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.persistence.entities.Item;
import pl.konrad_wajs.order_items.persistence.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    private OrderRepository orderRepository;

    @Autowired
    public ItemMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ItemDTO toDTO(Item item) {
        if (item == null) {
            return null;
        }

        return ItemDTO.builder()
                .id(item.getId())
                .size(item.getSize())
                .color(item.getColor())
                .build();
    }

    public Item toEntity(ItemDTO item, Long orderId) {
        if (item == null) {
            return null;
        }

        return Item.builder()
                .id(item.getId())
                .size(item.getSize())
                .color(item.getColor())
                .order(orderId == null ? null : orderRepository.findById(orderId).get())
                .build();
    }

    public List<ItemDTO> toDTOs(List<Item> items) {
        if (items == null) {
            return null;
        }
        return items.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }

    public List<Item> toEntities(List<ItemDTO> items, Long orderId) {
        if (items == null) {
            return null;
        }
        return items.stream().map(dto -> toEntity(dto, orderId)).collect(Collectors.toList());
    }
}
