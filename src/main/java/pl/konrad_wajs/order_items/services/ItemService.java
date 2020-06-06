package pl.konrad_wajs.order_items.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.konrad_wajs.order_items.dto.ItemDTO;
import pl.konrad_wajs.order_items.exceptions.EmptyRequiredFieldException;
import pl.konrad_wajs.order_items.exceptions.ObjectIsNullException;
import pl.konrad_wajs.order_items.mappers.ItemMapper;
import pl.konrad_wajs.order_items.persistence.entities.Item;
import pl.konrad_wajs.order_items.persistence.repositories.ItemRepository;

@Service
@Transactional
public class ItemService {

    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {

        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    protected ItemDTO save(ItemDTO itemDTO, Long orderId) {
        validateItemObject(itemDTO);

        Item item = itemMapper.toEntity(itemDTO, orderId);
        item.setId(null);

        return itemMapper.toDTO(itemRepository.save(item));
    }

    private void validateItemObject(ItemDTO item) {
        if (item == null) {
            throw new ObjectIsNullException(ItemDTO.class.getName());
        }
        if (StringUtils.isEmpty(item.getSize())) {
            throw new EmptyRequiredFieldException("size");
        }
        if (StringUtils.isEmpty(item.getColor())) {
            throw new EmptyRequiredFieldException("color");
        }
    }
}
