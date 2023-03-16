package zw.co.jugaad.usecases.store;




import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Product;
import zw.co.jugaad.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    List<Store> getAll();

    List<Store> searchByName(String searchText);

    Optional<Store> getById(Identity id);

    List<Product> getProductsById(Identity id);
}
