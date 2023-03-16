package zw.co.jugaad.usecases.order;


import zw.co.jugaad.domain.Order;

public class DeleteOrderUseCase extends UpdateOrderUseCase {

    public DeleteOrderUseCase(OrderRepository repository) {
        super(repository);
    }

    @Override
    protected Order updateStatus(Order order) {
        return order.delete();
    }
}
