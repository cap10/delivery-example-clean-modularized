package zw.co.jugaad.data.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Store;


import java.util.HashSet;
import java.util.Set;

import static zw.co.jugaad.data.entities.IdConverter.convertId;


@AllArgsConstructor
@Entity(name = "store")
@EqualsAndHashCode(of = {"name", "address"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "store")
@ToString(of = {"name", "address"})
public class StoreData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "cousine_id", nullable = false)
    private CousineData cousine;

    @OneToMany(mappedBy = "store")
    private Set<ProductData> products;

    // TODO: test
    public static StoreData from(Store store) {
        return new StoreData(
                convertId(store.getId()),
                store.getName(),
                store.getAddress(),
                CousineData.from(store.getCousine()),
                new HashSet<>());
    }

    // TODO: test
    public Store fromThis() {
        return new Store(
                new Identity(id),
                name,
                address,
                cousine.fromThis()
        );
    }
}
