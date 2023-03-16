package zw.co.jugaad.usecases.cousine;


import lombok.Value;

import zw.co.jugaad.domain.Cousine;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;

public class GetAllCousinesUseCase extends UseCase<GetAllCousinesUseCase.InputValues, GetAllCousinesUseCase.OutputValues> {
    private CousineRepository repository;

    public GetAllCousinesUseCase(CousineRepository repository) {
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
        private final List<Cousine> cousines;
    }
}
