package zw.co.jugaad.usecases.order;


import zw.co.jugaad.domain.Order;

public class DeliveryOrderUseCase extends UpdateOrderUseCase {
    public DeliveryOrderUseCase(OrderRepository repository) {
        super(repository);
    }

    @Override
    protected Order updateStatus(Order order) {
        order.delivery();

        return repository.persist(order.delivery());
    }
}
