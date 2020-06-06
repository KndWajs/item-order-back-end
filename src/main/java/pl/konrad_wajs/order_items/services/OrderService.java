package pl.konrad_wajs.order_items.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.mappers.OrderMapper;
import pl.konrad_wajs.order_items.persistence.repositories.OrderRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDTO addOrder(OrderDTO orderDTO) {
        //TODO WIP
        orderDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
        orderDTO.setItems(null);

        return orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }


    public List<OrderDTO> getAllOrders(){

        return null;
    }
}
