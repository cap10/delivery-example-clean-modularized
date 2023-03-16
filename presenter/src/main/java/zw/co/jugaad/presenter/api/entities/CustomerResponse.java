package zw.co.jugaad.presenter.api.entities;


import lombok.Value;
import zw.co.jugaad.domain.Customer;


@Value
public class CustomerResponse {
    private final String name;
    private final String email;
    private final String address;

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
