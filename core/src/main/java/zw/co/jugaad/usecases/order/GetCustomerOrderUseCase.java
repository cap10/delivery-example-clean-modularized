package zw.co.jugaad.usecases.order;


import lombok.Value;

import zw.co.jugaad.domain.Customer;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Order;
import zw.co.jugaad.usecases.UseCase;

public class GetCustomerOrderUseCase extends UseCase<GetCustomerOrderUseCase.InputValues, GetCustomerOrderUseCase.OutputValues> {
    private GetOrderUseCase getOrderUseCase;

    public GetCustomerOrderUseCase(GetOrderUseCase getOrderUseCase) {
        this.getOrderUseCase = getOrderUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Order order = getOrderUseCase
                .execute(new GetOrderUseCase.InputValues(input.getId()))
                .getOrder();

        return new OutputValues(order.getCustomer());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Customer customer;
    }
}
