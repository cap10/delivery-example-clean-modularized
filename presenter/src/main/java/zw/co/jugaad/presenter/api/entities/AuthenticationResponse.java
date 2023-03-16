package zw.co.jugaad.presenter.api.entities;

import lombok.Value;

@Value
public class AuthenticationResponse {
    private boolean success = true;
    private String token;
}
