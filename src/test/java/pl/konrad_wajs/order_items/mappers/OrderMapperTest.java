package pl.konrad_wajs.order_items.mappers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.konrad_wajs.order_items.base.OrderGenerator;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.persistence.entities.Order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {
    @InjectMocks
    private OrderMapper orderMapper;

    @Mock
    private ItemMapper itemMapper;

    @Before
    public void setUp() {
        when(itemMapper.toDTOs(any())).thenReturn(null);
        when(itemMapper.toEntities(any(), any())).thenReturn(null);
    }

    @Test
    public void shouldReturnOrderDTOWhenMapOrderEntity() {
        // given
        Order orderEntity = OrderGenerator.getSampleOrderEntity();
        OrderDTO orderDTO = OrderGenerator.getSampleOrderDTO();

        // when
        OrderDTO returnedOrderDTO = orderMapper.toDTO(orderEntity);

        // then
        assertEquals(orderDTO, returnedOrderDTO);
    }

    @Test
    public void shouldReturnOrderEntityWhenMapOrderDTO() {
        // given
        Order orderEntity = OrderGenerator.getSampleOrderEntity();
        OrderDTO orderDTO = OrderGenerator.getSampleOrderDTO();

        // when
        Order returnedOrderEntity = orderMapper.toEntity(orderDTO);

        // then
        assertEquals(orderEntity, returnedOrderEntity);
    }

    @Test
    public void shouldReturnOrderEntityWithNullsWhenMapOrderDTOWithNulls() {
        // given
        Order orderEntity = new Order();
        OrderDTO orderDTO = new OrderDTO();

        // when
        Order returnedOrderEntity = orderMapper.toEntity(orderDTO);

        // then
        assertEquals(orderEntity, returnedOrderEntity);
    }

    @Test
    public void shouldReturnOrderDtoWithNullsWhenMapOrderEntityWithNulls() {
        // given
        Order orderEntity = new Order();
        OrderDTO orderDTO = new OrderDTO();

        // when
        OrderDTO returnedOrderDto = orderMapper.toDTO(orderEntity);

        // then
        assertEquals(orderDTO, returnedOrderDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        Order returnedOrderEntity = orderMapper.toEntity(null);

        // then
        assertNull(returnedOrderEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        OrderDTO returnedOrderDto = orderMapper.toDTO(null);

        // then
        assertNull(returnedOrderDto);
    }
}
