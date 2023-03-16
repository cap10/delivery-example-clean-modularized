package zw.co.jugaad.usecases.product;





import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();

    Optional<Product> getById(Identity id);

    List<Product> searchByNameOrDescription(String searchText);

    List<Product> searchProductsByStoreAndProductsId(Identity storeId, List<Identity> productsId);
}
