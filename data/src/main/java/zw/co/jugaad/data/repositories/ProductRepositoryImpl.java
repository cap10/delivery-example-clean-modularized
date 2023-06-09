package zw.co.jugaad.data.repositories;


import org.springframework.stereotype.Component;
import zw.co.jugaad.data.entities.ProductData;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Product;
import zw.co.jugaad.usecases.product.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private JpaProductRepository repository;

    public ProductRepositoryImpl(JpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return repository
                .findAll()
                .stream()
                .map(ProductData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getById(Identity id) {
        return repository
                .findById(id.getNumber())
                .map(ProductData::fromThis);
    }

    @Override
    public List<Product> searchByNameOrDescription(String searchText) {
        return repository
                .findByNameContainingOrDescriptionContainingAllIgnoreCase(searchText, searchText)
                .stream()
                .map(ProductData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchProductsByStoreAndProductsId(Identity storeId, List<Identity> productsId) {
        return repository
                .findByStoreIdAndIdIsIn(storeId.getNumber(), createListOfLong(productsId))
                .stream()
                .map(ProductData::fromThis)
                .collect(Collectors.toList());
    }

    private List<Long> createListOfLong(List<Identity> productsId) {
        return productsId
                .stream()
                .map(Identity::getNumber)
                .collect(Collectors.toList());
    }
}
