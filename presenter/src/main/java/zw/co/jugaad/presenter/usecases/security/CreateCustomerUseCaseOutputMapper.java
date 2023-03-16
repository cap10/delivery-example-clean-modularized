package zw.co.jugaad.presenter.usecases.security;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import zw.co.jugaad.domain.Customer;
import zw.co.jugaad.presenter.api.entities.ApiResponse;

import java.net.URI;

public final class CreateCustomerUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(Customer customer, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/Customer/{id}")
                .buildAndExpand(customer.getId().getNumber())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "registered successfully"));
    }
}
