package zw.co.jugaad.presenter.api.order;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.jugaad.presenter.api.entities.ApiResponse;
import zw.co.jugaad.presenter.api.entities.CustomerResponse;
import zw.co.jugaad.presenter.api.entities.OrderRequest;
import zw.co.jugaad.presenter.api.entities.OrderResponse;
import zw.co.jugaad.presenter.usecases.security.CurrentUser;
import zw.co.jugaad.presenter.usecases.security.UserPrincipal;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Order")
public interface OrderResource {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ResponseEntity<ApiResponse>> create(@CurrentUser UserPrincipal userPrincipal,
                                                          HttpServletRequest httpServletRequest,
                                                          @Valid @RequestBody OrderRequest orderRequest);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<OrderResponse> getById(@PathVariable Long id);

    @GetMapping("/{id}/customer")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<CustomerResponse> getCustomerById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ApiResponse> delete(@PathVariable Long id);

    @PostMapping("/{id}/payment")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ApiResponse> pay(@PathVariable Long id);

    @PostMapping("/{id}/delivery")
    @PreAuthorize("hasRole('USER')")
    CompletableFuture<ApiResponse> delivery(@PathVariable Long id);
}
