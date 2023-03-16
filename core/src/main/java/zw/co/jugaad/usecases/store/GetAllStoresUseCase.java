package zw.co.jugaad.usecases.store;


import lombok.Value;

import zw.co.jugaad.domain.Store;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;

public class GetAllStoresUseCase extends UseCase<GetAllStoresUseCase.InputValues, GetAllStoresUseCase.OutputValues> {
    private StoreRepository repository;

    public GetAllStoresUseCase(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAll());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Store> stores;
    }
}
