package zw.co.jugaad.presenter.api.customer;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.jugaad.presenter.api.entities.ApiResponse;
import zw.co.jugaad.presenter.api.entities.AuthenticationResponse;
import zw.co.jugaad.presenter.api.entities.SignInRequest;
import zw.co.jugaad.presenter.api.entities.SignUpRequest;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Customer")
public interface CustomerResource {

    @PostMapping
    CompletableFuture<ResponseEntity<ApiResponse>> signUp(
            @Valid @RequestBody SignUpRequest request, HttpServletRequest httpServletRequest);

    @PostMapping("/auth")
    CompletableFuture<ResponseEntity<AuthenticationResponse>> signIn(@Valid @RequestBody SignInRequest request);
}
