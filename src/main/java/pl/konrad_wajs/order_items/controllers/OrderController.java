package pl.konrad_wajs.order_items.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.konrad_wajs.order_items.persistence.entities.Order;
import pl.konrad_wajs.order_items.persistence.repositories.OrderRepository;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping(path = "/orders/")
    public @ResponseBody
    Order addOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}