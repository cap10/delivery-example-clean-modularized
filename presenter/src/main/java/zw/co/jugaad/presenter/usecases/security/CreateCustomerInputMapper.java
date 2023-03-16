package zw.co.jugaad.presenter.usecases.security;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.jugaad.presenter.api.entities.SignUpRequest;
import zw.co.jugaad.usecases.customer.CreateCustomerUseCase;


@Service
public class CreateCustomerInputMapper {
    private PasswordEncoder passwordEncoder;

    public CreateCustomerInputMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CreateCustomerUseCase.InputValues map(SignUpRequest signUpRequest) {
        return new CreateCustomerUseCase.InputValues(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getAddress(),
                passwordEncoder.encode(signUpRequest.getPassword()));
    }
}
