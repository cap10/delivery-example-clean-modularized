package zw.co.jugaad.presenter.api.customer;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import zw.co.jugaad.presenter.api.entities.ApiResponse;
import zw.co.jugaad.presenter.api.entities.AuthenticationResponse;
import zw.co.jugaad.presenter.api.entities.SignInRequest;
import zw.co.jugaad.presenter.api.entities.SignUpRequest;
import zw.co.jugaad.presenter.usecases.security.AuthenticateCustomerUseCase;
import zw.co.jugaad.presenter.usecases.security.AuthenticateCustomerUseCaseInputMapper;
import zw.co.jugaad.presenter.usecases.security.AuthenticateCustomerUseCaseOutputMapper;
import zw.co.jugaad.presenter.usecases.security.CreateCustomerInputMapper;
import zw.co.jugaad.presenter.usecases.security.CreateCustomerUseCaseOutputMapper;
import zw.co.jugaad.usecases.UseCaseExecutor;
import zw.co.jugaad.usecases.customer.CreateCustomerUseCase;

import java.util.concurrent.CompletableFuture;

@Component
public class CustomerController implements CustomerResource {
    private UseCaseExecutor useCaseExecutor;
    private CreateCustomerUseCase createCustomerUseCase;
    private CreateCustomerInputMapper createCustomerUseCaseInputMapper;
    private AuthenticateCustomerUseCase authenticateCustomerUseCase;

    public CustomerController(UseCaseExecutor useCaseExecutor,
                              CreateCustomerUseCase createCustomerUseCase,
                              CreateCustomerInputMapper createCustomerUseCaseInputMapper,
                              AuthenticateCustomerUseCase authenticateCustomerUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCustomerUseCase = createCustomerUseCase;
        this.createCustomerUseCaseInputMapper = createCustomerUseCaseInputMapper;
        this.authenticateCustomerUseCase = authenticateCustomerUseCase;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> signUp(@Valid @RequestBody SignUpRequest signUpRequest,
                                                                 HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createCustomerUseCase,
                createCustomerUseCaseInputMapper.map(signUpRequest),
                (outputValues) -> CreateCustomerUseCaseOutputMapper.map(outputValues.getCustomer(), httpServletRequest));
    }

    @Override
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return useCaseExecutor.execute(
                authenticateCustomerUseCase,
                AuthenticateCustomerUseCaseInputMapper.map(signInRequest),
                AuthenticateCustomerUseCaseOutputMapper::map);
    }
}
