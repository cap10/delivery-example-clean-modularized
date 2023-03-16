package zw.co.jugaad.presenter.api.entities;


import lombok.Value;
import zw.co.jugaad.domain.Order;
import zw.co.jugaad.domain.Status;

import java.time.Instant;
import java.util.List;

import static zw.co.jugaad.data.entities.IdConverter.convertId;


@Value
public class OrderResponse {
    private final Long id;
    private final Instant date;
    private final CustomerResponse customer;
    private final StoreResponse store;
    private final String contact;
    private final Double total;
    private final Status status;
    private final Instant lastUpdate;
    private final List<OrderResponseItem> orderItems;

    public static OrderResponse from (Order order) {
        return new OrderResponse(
                convertId(order.getId()),
                order.getCreatedAt(),
                CustomerResponse.from(order.getCustomer()),
                StoreResponse.from(order.getStore()),
                order.getCustomer().getName(),
                order.getTotal(),
                order.getStatus(),
                order.getUpdatedAt(),
                OrderResponseItem.from(order.getOrderItems())
        );
    }
}
