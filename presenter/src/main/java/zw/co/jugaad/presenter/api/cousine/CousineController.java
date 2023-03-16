package zw.co.jugaad.presenter.api.cousine;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.presenter.api.entities.CousineResponse;
import zw.co.jugaad.presenter.api.entities.StoreResponse;
import zw.co.jugaad.usecases.UseCaseExecutor;
import zw.co.jugaad.usecases.cousine.GetAllCousinesUseCase;
import zw.co.jugaad.usecases.cousine.GetStoresByCousineUseCase;
import zw.co.jugaad.usecases.cousine.SearchCousineByNameUseCase;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CousineController implements CousineResource {
    private UseCaseExecutor useCaseExecutor;
    private GetAllCousinesUseCase getAllCousinesUseCase;
    private GetStoresByCousineUseCase getStoresByCousineUseCase;
    private SearchCousineByNameUseCase searchCousineByNameUseCase;

    public CousineController(UseCaseExecutor useCaseExecutor,
                             GetAllCousinesUseCase getAllCousinesUseCase,
                             GetStoresByCousineUseCase getStoresByCousineUseCase,
                             SearchCousineByNameUseCase searchCousineByNameUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getAllCousinesUseCase = getAllCousinesUseCase;
        this.getStoresByCousineUseCase = getStoresByCousineUseCase;
        this.searchCousineByNameUseCase = searchCousineByNameUseCase;
    }

    @Override
    public CompletableFuture<List<StoreResponse>> getStoresByCousineId(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getStoresByCousineUseCase,
                new GetStoresByCousineUseCase.InputValues(new Identity(id)),
                (outputValues) -> StoreResponse.from(outputValues.getStores()));
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousines() {
        return useCaseExecutor.execute(
                getAllCousinesUseCase,
                new GetAllCousinesUseCase.InputValues(),
                (outputValues) -> CousineResponse.from(outputValues.getCousines()));
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousinesByNameMatching(@PathVariable String text) {
        return useCaseExecutor.execute(
                searchCousineByNameUseCase,
                new SearchCousineByNameUseCase.InputValues(text),
                (outputValues) -> CousineResponse.from(outputValues.getCousines()));
    }
}
