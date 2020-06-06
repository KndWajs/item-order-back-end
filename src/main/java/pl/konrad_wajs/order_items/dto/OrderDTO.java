package pl.konrad_wajs.order_items.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private String name;

    private Integer age;

    private Timestamp creationDate;

    private List<ItemDTO> items;
}
