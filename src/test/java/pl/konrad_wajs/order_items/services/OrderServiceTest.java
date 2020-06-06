package pl.konrad_wajs.order_items.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.konrad_wajs.order_items.base.AbstractIntegrationTestBase;
import pl.konrad_wajs.order_items.base.ItemGenerator;
import pl.konrad_wajs.order_items.base.OrderGenerator;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.exceptions.EmptyRequiredFieldException;
import pl.konrad_wajs.order_items.exceptions.ObjectIsNullException;
import pl.konrad_wajs.order_items.mappers.OrderMapper;
import pl.konrad_wajs.order_items.persistence.entities.Item;
import pl.konrad_wajs.order_items.persistence.entities.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class OrderServiceTest extends AbstractIntegrationTestBase {
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void shouldReturnTwoOrdersDTOWhenGetAllNotArchivalOrders() {
        // given
        entityManager.persist(createOrder());
        entityManager.persist(createOrder());

        // when
        List<OrderDTO> orders = this.orderService.getAllOrders();

        // then
        assertEquals(orders.size(), 2);
    }

    @Test
    public void shouldReturnEmptyArrayWhenThereIsNoOrdersInDb() {
        // given
        // when
        List<OrderDTO> orders = this.orderService.getAllOrders();

        // then
        assertEquals(orders.size(), 0);
    }

    @Test
    public void shouldSaveOrder() {
        // given
        OrderDTO orderDTO = orderMapper.toDTO(createOrder());

        // when
        OrderDTO order = this.orderService.save(orderDTO);
        List<OrderDTO> savedOrders = this.orderService.getAllOrders();
        OrderDTO savedOrder = savedOrders.get(0);

        // then
        assertEquals(savedOrders.size(), 1);
        assertEquals(orderDTO.getName(), savedOrder.getName());
    }

    @Test
    public void shouldSaveOrderWithoutItems() {
        // given
        OrderDTO orderDTO = orderMapper.toDTO(createOrder());
        orderDTO.setItems(null);

        // when
        OrderDTO order = this.orderService.save(orderDTO);
        List<OrderDTO> savedOrders = this.orderService.getAllOrders();
        OrderDTO savedOrder = savedOrders.get(0);

        // then
        assertEquals(savedOrders.size(), 1);
        assertEquals(orderDTO.getName(), savedOrder.getName());
    }

    @Test
    public void shouldSaveOrderWhenIdIsNotNull() {
        // given
        OrderDTO orderDTO = orderMapper.toDTO(createOrder());
        orderDTO.setId(323L);

        // when
        OrderDTO order = this.orderService.save(orderDTO);
        List<OrderDTO> savedOrders = this.orderService.getAllOrders();
        OrderDTO savedOrder = savedOrders.get(0);

        // then
        assertEquals(savedOrders.size(), 1);
        assertEquals(orderDTO.getName(), savedOrder.getName());
    }


    @Test(expected = ObjectIsNullException.class)
    public void shouldThrowExceptionWhenSavedOrderDTOIsNull() {
        // given
        OrderDTO orderDTO = null;
        // when
        // then
        this.orderService.save(orderDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedOrderDTOHasEmptyNameString() {
        // given
        OrderDTO orderDTO = orderMapper.toDTO(createOrder());
        orderDTO.setName("");

        // when
        // then
        this.orderService.save(orderDTO);
    }

    @Test(expected = EmptyRequiredFieldException.class)
    public void shouldThrowExceptionWhenSavedOrderDTOHasNullInNameString() {
        // given
        OrderDTO orderDTO = orderMapper.toDTO(createOrder());
        orderDTO.setName(null);

        // when
        // then
        this.orderService.save(orderDTO);
    }

    private Order createOrder() {
        Order order = OrderGenerator.getSampleOrderEntity();

        Item item = ItemGenerator.getSampleItemEntity();
        entityManager.persist(item);

        order.setItems(new ArrayList<>());
        order.getItems().add(item);

        return order;
    }
}