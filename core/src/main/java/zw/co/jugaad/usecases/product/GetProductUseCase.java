package zw.co.jugaad.usecases.product;


import lombok.Value;

import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.NotFoundException;
import zw.co.jugaad.domain.Product;
import zw.co.jugaad.usecases.UseCase;

public class GetProductUseCase extends UseCase<GetProductUseCase.InputValues, GetProductUseCase.OutputValues> {
    private ProductRepository repository;

    public GetProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Identity id = input.getId();

        return repository
                .getById(id)
                .map(OutputValues::new)
                .orElseThrow(() -> new NotFoundException("Product %s not found", id.getNumber()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Product product;
    }
}
