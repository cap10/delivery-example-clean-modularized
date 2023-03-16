package zw.co.jugaad.data.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.OrderItem;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static zw.co.jugaad.data.entities.IdConverter.convertId;


@AllArgsConstructor
@Entity(name = "orderItem")
@EqualsAndHashCode(of = {"order", "product", "price", "quantity", "total"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "order_item")
@ToString(of = {"order", "product", "price", "quantity", "total"})
public class OrderItemData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderData order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductData product;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double total;

    // TODO: test
    public static Set<OrderItemData> from(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(OrderItemData::from)
                .collect(Collectors.toSet());
    }

    // TODO: test
    public static OrderItemData from(OrderItem orderItem) {
        return new OrderItemData(
                convertId(orderItem.getId()),
                null,
                ProductData.from(orderItem.getProduct()),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity(),
                orderItem.getTotal()
        );
    }

    // TODO: test
    public static OrderItemData newInstance(ProductData productData, Integer quantity) {
        return new OrderItemData(
                null,
                null,
                productData,
                productData.getPrice(),
                quantity,
                quantity * productData.getPrice()
        );
    }

    // TODO: test
    public OrderItem fromThis() {
        return new OrderItem(
                new Identity(id),
                quantity,
                product.fromThis(),
                total
        );
    }
}
