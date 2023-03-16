package zw.co.jugaad.usecases.order;


import zw.co.jugaad.domain.Order;

public class PayOrderUseCase extends UpdateOrderUseCase {
    public PayOrderUseCase(OrderRepository repository) {
        super(repository);
    }

    @Override
    protected Order updateStatus(Order order) {
        return repository.persist(order.pay());
    }
}
