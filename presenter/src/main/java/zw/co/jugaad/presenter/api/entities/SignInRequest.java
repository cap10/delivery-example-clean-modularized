package zw.co.jugaad.presenter.api.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;



@Value
public class SignInRequest {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 6, max = 50)
    private final String password;
}
