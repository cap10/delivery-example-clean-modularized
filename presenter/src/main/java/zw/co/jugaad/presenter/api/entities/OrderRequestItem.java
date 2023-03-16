package zw.co.jugaad.presenter.api.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;



@Value
public class OrderRequestItem {
    @NotNull
    private final Long id;

    @Min(1)
    @NotNull
    private final Integer quantity;
}
