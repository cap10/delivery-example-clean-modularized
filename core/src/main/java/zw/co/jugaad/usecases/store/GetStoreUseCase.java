package zw.co.jugaad.usecases.store;


import lombok.Value;

import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.domain.NotFoundException;
import zw.co.jugaad.domain.Store;
import zw.co.jugaad.usecases.UseCase;

public class GetStoreUseCase extends UseCase<GetStoreUseCase.InputValues, GetStoreUseCase.OutputValues> {
    private StoreRepository repository;

    public GetStoreUseCase(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Identity id = input.getId();

        return repository
                .getById(id)
                .map(OutputValues::new)
                .orElseThrow(() -> new NotFoundException("Store %s not found", id.getNumber()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Store store;
    }
}
