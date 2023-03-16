package zw.co.jugaad.usecases.store;


import lombok.Value;

import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.NotFoundException;
import zw.co.jugaad.domain.Product;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;

public class GetProductsByStoreUseCase extends UseCase<GetProductsByStoreUseCase.InputValues, GetProductsByStoreUseCase.OutputValues> {
    private StoreRepository repository;

    public GetProductsByStoreUseCase(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues inputValues) {
        Identity id = inputValues.getId();

        List<Product> products = repository.getProductsById(id);

        if (products.isEmpty()) {
            throw new NotFoundException("No store found by identity: " + id.getNumber());
        }

        return new OutputValues(products);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Product> products;
    }
}
