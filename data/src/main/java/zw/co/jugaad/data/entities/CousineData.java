package zw.co.jugaad.data.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zw.co.jugaad.domain.Cousine;
import zw.co.jugaad.domain.Identity;


import java.util.HashSet;
import java.util.Set;


import static zw.co.jugaad.data.entities.IdConverter.convertId;

@AllArgsConstructor
@Entity(name = "cousine")
@EqualsAndHashCode(of = {"name"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "cousine")
@ToString(of = {"name"})
public class CousineData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "cousine",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<StoreData> stores;

    // TODO: test
    public void addStore(StoreData store) {
        if (this.stores == null) {
            this.stores = new HashSet<>();
        }

        store.setCousine(this);
        this.stores.add(store);
    }

    // TODO: test
    public static CousineData newInstance(String name) {
        return new CousineData(null, name, new HashSet<>());
    }

    // TODO: test
    public static CousineData from(Cousine cousine) {
        return new CousineData(
                convertId(cousine.getId()),
                cousine.getName(),
                new HashSet<>()
        );
    }

    public Cousine fromThis() {
        return new Cousine(
                new Identity(id),
                name
        );
    }
}
