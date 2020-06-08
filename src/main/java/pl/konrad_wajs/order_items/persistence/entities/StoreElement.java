package pl.konrad_wajs.order_items.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.konrad_wajs.order_items.enums.ItemColor;
import pl.konrad_wajs.order_items.enums.ItemSize;

import javax.persistence.*;

@Entity
@Table(name = "store_elements")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreElement implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private ItemSize size;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private ItemColor color;

    @Column(name = "amount")
    private int amount;
}
