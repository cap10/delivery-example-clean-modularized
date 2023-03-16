package zw.co.jugaad.presenter.api.order;


import org.springframework.security.core.userdetails.UserDetails;
import zw.co.jugaad.domain.Customer;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.presenter.api.entities.OrderRequest;
import zw.co.jugaad.presenter.api.entities.OrderRequestItem;
import zw.co.jugaad.presenter.usecases.security.UserPrincipal;
import zw.co.jugaad.usecases.order.CreateOrderUseCase;

import java.util.List;
import java.util.stream.Collectors;

public final class CreateOrderInputMapper {

    public static CreateOrderUseCase.InputValues map(OrderRequest orderRequest, UserDetails userDetails) {
        return new CreateOrderUseCase.InputValues(
                map(userDetails),
                new Identity(orderRequest.getStoreId()),
                map(orderRequest.getOrderItems())
        );
    }

    public static Customer map(UserDetails userDetails) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;

        return new Customer(
                new Identity(userPrincipal.getId()),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getAddress(),
                userPrincipal.getPassword()
        );
    }

    private static List<CreateOrderUseCase.InputItem> map(List<OrderRequestItem> orderItems) {
        return orderItems
                .parallelStream()
                .map(CreateOrderInputMapper::map)
                .collect(Collectors.toList());
    }

    private static CreateOrderUseCase.InputItem map(OrderRequestItem orderRequestItem) {
        return new CreateOrderUseCase.InputItem(new Identity(orderRequestItem.getId()), orderRequestItem.getQuantity());
    }
}
