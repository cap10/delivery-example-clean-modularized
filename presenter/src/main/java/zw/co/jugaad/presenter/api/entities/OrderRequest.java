package zw.co.jugaad.presenter.api.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value
public class OrderRequest {
    @NotNull
    private final Long storeId;

    @NotEmpty
    private final List<OrderRequestItem> orderItems;
}
