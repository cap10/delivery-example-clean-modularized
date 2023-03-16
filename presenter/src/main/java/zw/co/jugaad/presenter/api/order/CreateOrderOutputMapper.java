package zw.co.jugaad.presenter.api.order;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.jugaad.domain.Order;
import zw.co.jugaad.presenter.api.entities.ApiResponse;

import java.net.URI;

public final class CreateOrderOutputMapper {

    public static ResponseEntity<ApiResponse> map(Order order, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/Order/{id}")
                .buildAndExpand(order.getId().getNumber())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "order created successfully"));
    }
}
