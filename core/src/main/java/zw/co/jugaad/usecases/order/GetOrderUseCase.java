package zw.co.jugaad.usecases.order;


import lombok.Value;

import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.NotFoundException;
import zw.co.jugaad.domain.Order;
import zw.co.jugaad.usecases.UseCase;

public class GetOrderUseCase extends UseCase<GetOrderUseCase.InputValues, GetOrderUseCase.OutputValues> {
    private OrderRepository repository;

    public GetOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        final Identity id = input.getId();

        return repository.getById(id)
                .map(OutputValues::new)
                .orElseThrow(() -> new NotFoundException("Order %s not found", id.getNumber()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Order order;
    }
}
