package zw.co.jugaad.usecases.cousine;


import lombok.Value;

import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.NotFoundException;
import zw.co.jugaad.domain.Store;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;

public class GetStoresByCousineUseCase extends UseCase<GetStoresByCousineUseCase.InputValues, GetStoresByCousineUseCase.OutputValues> {
    private CousineRepository repository;

    public GetStoresByCousineUseCase(CousineRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Identity id = input.getId();

        List<Store> stores = repository.getStoresById(id);

        if (stores.isEmpty()) {
            throw new NotFoundException("Cousine %s not found", id.getNumber());
        }

        return new OutputValues(stores);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Store> stores;
    }
}
