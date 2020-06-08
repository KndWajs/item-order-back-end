package pl.konrad_wajs.order_items.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.exceptions.EmptyRequiredFieldException;
import pl.konrad_wajs.order_items.exceptions.NoItemAvailableException;
import pl.konrad_wajs.order_items.exceptions.ObjectIsNullException;
import pl.konrad_wajs.order_items.mappers.OrderMapper;
import pl.konrad_wajs.order_items.persistence.entities.Order;
import pl.konrad_wajs.order_items.persistence.entities.StoreElement;
import pl.konrad_wajs.order_items.persistence.repositories.OrderRepository;
import pl.konrad_wajs.order_items.persistence.repositories.StoreRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private StoreRepository storeRepository;
    private OrderMapper orderMapper;
    private ItemService itemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ItemService itemService,
                        StoreRepository storeRepository) {

        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
        this.orderMapper = orderMapper;
        this.itemService = itemService;
    }

    public OrderDTO save(OrderDTO orderDTO) {
        validateOrderObject(orderDTO);
        OrderDTO savedOrder = saveOrderWithoutItems(orderDTO);

        if (!StringUtils.isEmpty(orderDTO.getItems())) {
            updateStore(orderDTO.getItems());
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

    private void updateStore(List<ItemDTO> items) {
        List<ItemDTO> temporaryBasket = new ArrayList<>(items);
        Iterator<ItemDTO> iterator = temporaryBasket.iterator();
        while (iterator.hasNext()){
            ItemDTO item = iterator.next();
            StoreElement storeElement = storeRepository.findStoreElementBySizeAndColor(item.getSize(), item.getColor());
            checkItemsAvailability(temporaryBasket, item, storeElement);
            storeElement.setAmount(storeElement.getAmount() - 1);
            iterator.remove();
            storeRepository.save(storeElement);
        }
    }

    private void checkItemsAvailability(List<ItemDTO> items, ItemDTO item, StoreElement storeElement) {

        long amountInBasket = items
                .stream()
                .filter(i -> i.getColor() == item.getColor() && i.getSize() == item.getSize())
                .count();
        if (storeElement == null || amountInBasket > storeElement.getAmount()) {
            throw new NoItemAvailableException(item);
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
