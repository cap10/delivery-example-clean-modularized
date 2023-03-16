package zw.co.jugaad.data.repositories;


import org.springframework.stereotype.Component;
import zw.co.jugaad.data.entities.OrderData;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Order;
import zw.co.jugaad.usecases.order.OrderRepository;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private JpaOrderRepository repository;

    public OrderRepositoryImpl(JpaOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order persist(Order order) {
        OrderData orderData = OrderData.from(order);

        return repository.save(orderData).fromThis();
    }

    @Override
    public Optional<Order> getById(Identity id) {
        return repository
                .findById(id.getNumber())
                .map(OrderData::fromThis);
    }
}
