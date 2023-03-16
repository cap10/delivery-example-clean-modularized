package zw.co.jugaad.usecases.store;


import lombok.Value;

import zw.co.jugaad.domain.Store;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;

public class SearchStoresByNameUseCase extends UseCase<SearchStoresByNameUseCase.InputValues, SearchStoresByNameUseCase.OutputValues> {
    private StoreRepository repository;

    public SearchStoresByNameUseCase(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.searchByName(input.getSearchText()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String searchText;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Store> stores;
    }
}

