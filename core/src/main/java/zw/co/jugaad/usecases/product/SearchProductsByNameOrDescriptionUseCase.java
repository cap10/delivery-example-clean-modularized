package zw.co.jugaad.usecases.product;


import lombok.Value;


import zw.co.jugaad.domain.Product;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;


public class SearchProductsByNameOrDescriptionUseCase extends UseCase<SearchProductsByNameOrDescriptionUseCase.InputValues, SearchProductsByNameOrDescriptionUseCase.OutputValues> {
    private ProductRepository repository;

    public SearchProductsByNameOrDescriptionUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.searchByNameOrDescription(input.getSearchText()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String searchText;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Product> products;
    }
}
