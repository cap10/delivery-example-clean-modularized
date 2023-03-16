package zw.co.jugaad.usecases.cousine;


import lombok.Value;

import zw.co.jugaad.domain.Cousine;
import zw.co.jugaad.usecases.UseCase;

import java.util.List;

public class SearchCousineByNameUseCase extends UseCase<SearchCousineByNameUseCase.InputValues, SearchCousineByNameUseCase.OutputValues> {

    private CousineRepository repository;

    public SearchCousineByNameUseCase(CousineRepository repository) {
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
        private final List<Cousine> cousines;
    }
}
