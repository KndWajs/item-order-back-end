package pl.konrad_wajs.order_items.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders/")
    public @ResponseBody
    OrderDTO addOrder(@RequestBody OrderDTO order) {
        return orderService.save(order);
    }

    @GetMapping(path = "/orders/")
    public @ResponseBody
    List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
}