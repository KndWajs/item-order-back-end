package pl.konrad_wajs.order_items.mappers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.konrad_wajs.order_items.base.ItemGenerator;
import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.persistence.entities.Item;
import pl.konrad_wajs.order_items.persistence.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ItemMapperTest {
    @InjectMocks
    private ItemMapper itemMapper;

    @Mock
    private OrderRepository orderRepository;;

    @Test
    public void shouldReturnItemDTOWhenMapItemEntity() {
        // given
        Item itemEntity = ItemGenerator.getSampleItemEntity();
        ItemDTO itemDTO = ItemGenerator.getSampleItemDTO();

        // when
        ItemDTO returnedItemDTO = itemMapper.toDTO(itemEntity);

        // then
        assertEquals(itemDTO, returnedItemDTO);
    }

    @Test
    public void shouldReturnItemEntityWhenMapItemDTO() {
        // given
        Item itemEntity = ItemGenerator.getSampleItemEntity();
        ItemDTO itemDTO = ItemGenerator.getSampleItemDTO();

        // when
        Item returnedItemEntity = itemMapper.toEntity(itemDTO, null);

        // then
        assertEquals(itemEntity, returnedItemEntity);
    }

    @Test
    public void shouldReturnItemEntityWithNullsWhenMapItemDTOWithNulls() {
        // given
        Item itemEntity = new Item();
        ItemDTO itemDTO = new ItemDTO();

        // when
        Item returnedItemEntity = itemMapper.toEntity(itemDTO, null);

        // then
        assertEquals(itemEntity, returnedItemEntity);
    }

    @Test
    public void shouldReturnItemDtoWithNullsWhenMapItemEntityWithNulls() {
        // given
        Item itemEntity = new Item();
        ItemDTO itemDTO = new ItemDTO();

        // when
        ItemDTO returnedItemDto = itemMapper.toDTO(itemEntity);

        // then
        assertEquals(itemDTO, returnedItemDto);
    }

    @Test
    public void shouldReturnNullWhenMapEntityWhichIsNull() {
        // given
        // when
        Item returnedItemEntity = itemMapper.toEntity(null, null);

        // then
        assertNull(returnedItemEntity);
    }

    @Test
    public void shouldReturnNullWhenMapDtoWhichIsNull() {
        // given
        // when
        ItemDTO returnedItemDto = itemMapper.toDTO(null);

        // then
        assertNull(returnedItemDto);
    }


    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyItemDTOList() {
        // given
        // when
        List<Item> returnedItemEntityList = itemMapper.toEntities(new ArrayList<>(), null);

        // then
        Assert.assertEquals(new ArrayList<>(), returnedItemEntityList);
    }

    @Test
    public void shouldReturnEmptyArrayWhenMapEmptyItemEntityList() {
        // given
        // when
        List<ItemDTO> returnedItemDtoList = itemMapper.toDTOs(new ArrayList<>());

        // then
        Assert.assertEquals(new ArrayList<>(), returnedItemDtoList);
    }

    @Test
    public void shouldReturnNullWhenMapEntityListWhichIsNull() {
        // given
        // when
        List<Item> returnedItemEntityList = itemMapper.toEntities(null, null);

        // then
        assertNull(returnedItemEntityList);
    }

    @Test
    public void shouldReturnNullWhenMapDtoListWhichIsNull() {
        // given
        // when
        List<ItemDTO> returnedItemDtoList = itemMapper.toDTOs(null);

        // then
        assertNull(returnedItemDtoList);
    }
}
