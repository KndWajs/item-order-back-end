package pl.konrad_wajs.order_items.base;

import pl.konrad_wajs.order_items.dto.OrderDTO;
import pl.konrad_wajs.order_items.persistence.entities.Order;

import java.sql.Timestamp;

public class OrderGenerator {

    public static Order getSampleOrderEntity() {
        return Order.builder()
                .name("sampleFirstName")
                .age(35)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static OrderDTO getSampleOrderDTO() {
        return OrderDTO.builder()
                .name("sampleFirstName")
                .age(35)
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
