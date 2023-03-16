package zw.co.jugaad.presenter.api.entities;


import lombok.Value;
import zw.co.jugaad.domain.Store;

import java.util.List;
import java.util.stream.Collectors;

import static zw.co.jugaad.data.entities.IdConverter.convertId;


@Value
public class StoreResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final Long cousineId;

    public static StoreResponse from(Store store) {
        return new StoreResponse(
                store.getId().getNumber(),
                store.getName(),
                store.getAddress(),
                convertId(store.getCousine().getId())
        );
    }

    public static List<StoreResponse> from(List<Store> stores) {
        return stores
                .parallelStream()
                .map(StoreResponse::from)
                .collect(Collectors.toList());
    }
}
