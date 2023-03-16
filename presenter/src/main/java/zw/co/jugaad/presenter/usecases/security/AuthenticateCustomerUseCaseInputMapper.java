package zw.co.jugaad.presenter.usecases.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import zw.co.jugaad.presenter.api.entities.SignInRequest;


public final class AuthenticateCustomerUseCaseInputMapper {
    public static AuthenticateCustomerUseCase.InputValues map(SignInRequest signInRequest) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword());

        return new AuthenticateCustomerUseCase.InputValues(auth);
    }
}
