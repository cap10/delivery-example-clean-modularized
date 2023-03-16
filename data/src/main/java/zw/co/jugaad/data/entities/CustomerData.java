package zw.co.jugaad.data.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zw.co.jugaad.domain.Customer;
import zw.co.jugaad.domain.Identity;

import static zw.co.jugaad.data.entities.IdConverter.convertId;


@AllArgsConstructor
@Entity(name = "customer")
@EqualsAndHashCode(of = {"name", "email", "address", "password"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "customer")
@ToString(of = {"name", "email", "address"})
public class CustomerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    // TODO: test
    public static CustomerData from(Customer customer) {
        return new CustomerData(
                convertId(customer.getId()),
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPassword()
        );
    }

    // TODO: test
    public Customer fromThis() {
        return new Customer(
                new Identity(id),
                name,
                email,
                address,
                password
        );
    }
}
