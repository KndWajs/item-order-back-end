package pl.konrad_wajs.order_items.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.persistence.entities.Order;

//TODO maybe change meppers to static
@Component
public class OrderMapper implements Mapper<OrderDTO, Order> {

    private ItemMapper itemMapper;

    @Autowired
    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        return OrderDTO.builder()
                .id(order.getId())
                .name(order.getName())
                .age(order.getAge())
                .creationDate(order.getCreationDate())
                .items(itemMapper.toDTOs(order.getItems()))
                .build();
    }

    @Override
    public Order toEntity(OrderDTO order) {
        if (order == null) {
            return null;
        }

        return Order.builder()
                .id(order.getId())
                .name(order.getName())
                .age(order.getAge())
                .creationDate(order.getCreationDate())
                .items(itemMapper.toEntities(order.getItems(), order.getId()))
                .build();
    }
}
