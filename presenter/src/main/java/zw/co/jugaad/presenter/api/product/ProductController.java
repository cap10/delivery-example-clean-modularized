package zw.co.jugaad.presenter.api.product;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import zw.co.jugaad.domain.Identity;
import zw.co.jugaad.presenter.api.entities.ProductResponse;
import zw.co.jugaad.usecases.UseCaseExecutor;
import zw.co.jugaad.usecases.product.GetAllProductsUseCase;
import zw.co.jugaad.usecases.product.GetProductUseCase;
import zw.co.jugaad.usecases.product.SearchProductsByNameOrDescriptionUseCase;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ProductController implements ProductResource {
    private UseCaseExecutor useCaseExecutor;
    private GetAllProductsUseCase getAllProductsUseCase;
    private GetProductUseCase getProductUseCase;
    private SearchProductsByNameOrDescriptionUseCase searchProductsByNameOrDescriptionUseCase;

    public ProductController(UseCaseExecutor useCaseExecutor,
                             GetAllProductsUseCase getAllProductsUseCase,
                             GetProductUseCase getProductUseCase,
                             SearchProductsByNameOrDescriptionUseCase searchProductsByNameOrDescriptionUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductUseCase = getProductUseCase;
        this.searchProductsByNameOrDescriptionUseCase = searchProductsByNameOrDescriptionUseCase;
    }

    @Override
    public CompletableFuture<List<ProductResponse>> getAllProducts() {
        return useCaseExecutor.execute(
                getAllProductsUseCase,
                new GetAllProductsUseCase.InputValues(),
                (outputValues) -> ProductResponse.from(outputValues.getProducts()));
    }

    @Override
    public CompletableFuture<ProductResponse> getByIdentity(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getProductUseCase,
                new GetProductUseCase.InputValues(new Identity(id)),
                (outputValues) -> ProductResponse.from(outputValues.getProduct()));
    }

    @Override
    public CompletableFuture<List<ProductResponse>> getByMatchingName(@PathVariable String text) {
        return useCaseExecutor.execute(
                searchProductsByNameOrDescriptionUseCase,
                new SearchProductsByNameOrDescriptionUseCase.InputValues(text),
                (outputValues) -> ProductResponse.from(outputValues.getProducts()));
    }
}
