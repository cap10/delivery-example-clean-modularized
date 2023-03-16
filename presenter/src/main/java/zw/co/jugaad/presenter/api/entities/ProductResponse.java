package zw.co.jugaad.presenter.api.entities;


import lombok.Value;
import zw.co.jugaad.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

import static zw.co.jugaad.data.entities.IdConverter.convertId;


@Value
public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final Long storeId;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                convertId(product.getId()),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                convertId(product.getStore().getId()));
    }

    public static List<ProductResponse> from(List<Product> products) {
        return products
                .parallelStream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }
}
