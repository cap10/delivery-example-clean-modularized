package zw.co.jugaad.presenter.api.cousine;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.jugaad.presenter.api.entities.CousineResponse;
import zw.co.jugaad.presenter.api.entities.StoreResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Cousine")
public interface CousineResource {
    @GetMapping("/{id}/stores")
    CompletableFuture<List<StoreResponse>> getStoresByCousineId(@PathVariable Long id);

    @GetMapping
    CompletableFuture<List<CousineResponse>> getAllCousines();

    @GetMapping("/search/{text}")
    CompletableFuture<List<CousineResponse>> getAllCousinesByNameMatching(@PathVariable String text);
}
