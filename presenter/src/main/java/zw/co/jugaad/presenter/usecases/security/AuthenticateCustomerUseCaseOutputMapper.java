package zw.co.jugaad.presenter.usecases.security;


import org.springframework.http.ResponseEntity;
import zw.co.jugaad.presenter.api.entities.AuthenticationResponse;


public final class AuthenticateCustomerUseCaseOutputMapper {
    public static ResponseEntity<AuthenticationResponse> map(AuthenticateCustomerUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new AuthenticationResponse(outputValues.getJwtToken()));
    }
}
