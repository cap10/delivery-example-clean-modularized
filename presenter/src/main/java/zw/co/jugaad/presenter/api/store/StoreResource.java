package zw.co.jugaad.presenter.api.store;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.jugaad.presenter.api.entities.ProductResponse;
import zw.co.jugaad.presenter.api.entities.StoreResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Store")
public interface StoreResource {

    @GetMapping
    CompletableFuture<List<StoreResponse>> getAll();

    @GetMapping("/search/{text}")
    CompletableFuture<List<StoreResponse>> getAllStoresByNameMatching(@PathVariable String text);

    @GetMapping("/{id}")
    CompletableFuture<StoreResponse> getStoreByIdentity(@PathVariable Long id);

    @GetMapping("/{id}/products")
    CompletableFuture<List<ProductResponse>> getProductsBy(@PathVariable Long id);

}
