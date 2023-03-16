package zw.co.jugaad.presenter.api.order;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.presenter.api.entities.ApiResponse;
import zw.co.jugaad.presenter.api.entities.CustomerResponse;
import zw.co.jugaad.presenter.api.entities.OrderRequest;
import zw.co.jugaad.presenter.api.entities.OrderResponse;
import zw.co.jugaad.presenter.usecases.security.CurrentUser;
import zw.co.jugaad.presenter.usecases.security.UserPrincipal;
import zw.co.jugaad.usecases.UseCaseExecutor;
import zw.co.jugaad.usecases.order.CreateOrderUseCase;
import zw.co.jugaad.usecases.order.DeleteOrderUseCase;
import zw.co.jugaad.usecases.order.DeliveryOrderUseCase;
import zw.co.jugaad.usecases.order.GetCustomerOrderUseCase;
import zw.co.jugaad.usecases.order.GetOrderUseCase;
import zw.co.jugaad.usecases.order.PayOrderUseCase;

import java.util.concurrent.CompletableFuture;

@Controller
public class OrderController implements OrderResource {
    private UseCaseExecutor useCaseExecutor;
    private CreateOrderUseCase createOrderUseCase;
    private GetOrderUseCase getOrderUseCase;
    private GetCustomerOrderUseCase getCustomerOrderUseCase;
    private DeleteOrderUseCase deleteOrderUseCase;
    private PayOrderUseCase payOrderUseCase;
    private DeliveryOrderUseCase deliveryOrderUseCase;

    public OrderController(UseCaseExecutor useCaseExecutor,
                           CreateOrderUseCase createOrderUseCase,
                           GetOrderUseCase getOrderUseCase,
                           GetCustomerOrderUseCase getCustomerOrderUseCase,
                           DeleteOrderUseCase deleteOrderUseCase,
                           PayOrderUseCase payOrderUseCase,
                           DeliveryOrderUseCase deliveryOrderUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.getCustomerOrderUseCase = getCustomerOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        this.payOrderUseCase = payOrderUseCase;
        this.deliveryOrderUseCase = deliveryOrderUseCase;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> create(@CurrentUser UserPrincipal userDetails,
                                                                 HttpServletRequest httpServletRequest,
                                                                 @Valid @RequestBody OrderRequest orderRequest) {
        return useCaseExecutor.execute(
                createOrderUseCase,
                CreateOrderInputMapper.map(orderRequest, userDetails),
                (outputValues) -> CreateOrderOutputMapper.map(outputValues.getOrder(), httpServletRequest)
        );
    }

    @Override
    public CompletableFuture<OrderResponse> getById(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getOrderUseCase,
                new GetOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> OrderResponse.from(outputValues.getOrder())
        );
    }

    @Override
    public CompletableFuture<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getCustomerOrderUseCase,
                new GetCustomerOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> CustomerResponse.from(outputValues.getCustomer())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> delete(@PathVariable Long id) {
        return useCaseExecutor.execute(
                deleteOrderUseCase,
                new DeleteOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> new ApiResponse(true, "Order successfully canceled")
        );
    }

    @Override
    public CompletableFuture<ApiResponse> pay(@PathVariable Long id) {
        return useCaseExecutor.execute(
                payOrderUseCase,
                new DeleteOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> new ApiResponse(true, "Order successfully paid")
        );
    }

    @Override
    public CompletableFuture<ApiResponse> delivery(@PathVariable Long id) {
        return useCaseExecutor.execute(
                deliveryOrderUseCase,
                new DeleteOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> new ApiResponse(true, "Order successfully delivered")
        );
    }
}
