package pl.konrad_wajs.order_items.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.exceptions.EmptyRequiredFieldException;
import pl.konrad_wajs.order_items.exceptions.ObjectIsNullException;
import pl.konrad_wajs.order_items.mappers.OrderMapper;
import pl.konrad_wajs.order_items.persistence.entities.Order;
import pl.konrad_wajs.order_items.persistence.repositories.OrderRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ItemService itemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ItemService itemService) {

        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.itemService = itemService;
    }

    public OrderDTO save(OrderDTO orderDTO) {
        validateOrderObject(orderDTO);

        OrderDTO savedOrder = saveOrderWithoutItems(orderDTO);

        if (!StringUtils.isEmpty(orderDTO)) {
            for (ItemDTO itemDTO : orderDTO.getItems()) {
                savedOrder.getItems().add(itemService.save(itemDTO, savedOrder.getId()));
            }
        }

        return savedOrder;
    }

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            orders.add(orderMapper.toDTO(order));
        }
        return orders;
    }

    private void validateOrderObject(OrderDTO order) {
        if (order == null) {
            throw new ObjectIsNullException(OrderDTO.class.getName());
        }
        if (StringUtils.isEmpty(order.getName())) {
            throw new EmptyRequiredFieldException("name");
        }
        if (StringUtils.isEmpty(order.getAge())) {
            throw new EmptyRequiredFieldException("age");
        }
    }

    private OrderDTO saveOrderWithoutItems(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setCreationDate(new Timestamp(System.currentTimeMillis()));
        order.setId(null);
        order.setItems(new ArrayList<>());

        return orderMapper.toDTO(orderRepository.save(order));
    }
}
