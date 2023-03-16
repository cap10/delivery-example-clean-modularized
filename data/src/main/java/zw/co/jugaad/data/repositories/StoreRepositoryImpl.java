package zw.co.jugaad.data.repositories;


import org.springframework.stereotype.Component;
import zw.co.jugaad.data.entities.ProductData;
import zw.co.jugaad.data.entities.StoreData;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.Product;
import zw.co.jugaad.domain.Store;
import zw.co.jugaad.usecases.store.StoreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StoreRepositoryImpl implements StoreRepository {
    private JpaStoreRepository repository;

    public StoreRepositoryImpl(JpaStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Store> getAll() {
        return repository
                .findAll()
                .parallelStream()
                .map(StoreData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Store> searchByName(String searchText) {
        return repository
                .findByNameContainingIgnoreCase(searchText)
                .parallelStream()
                .map(StoreData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Store> getById(Identity id) {
        return repository
                .findById(id.getNumber())
                .map(StoreData::fromThis);
    }

    @Override
    public List<Product> getProductsById(Identity id) {
        return repository
                .findProductsById(id.getNumber())
                .stream()
                .map(ProductData::fromThis)
                .collect(Collectors.toList());
    }
}
