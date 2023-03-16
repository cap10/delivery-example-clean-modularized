package zw.co.jugaad.presenter.api.entities;


import lombok.Value;
import zw.co.jugaad.domain.Cousine;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class CousineResponse {
    private final Long id;
    private final String name;

    private static CousineResponse from(Cousine cousine) {
        return new CousineResponse(cousine.getId().getNumber(), cousine.getName());
    }
    
    public static List<CousineResponse> from(List<Cousine> cousines) {
        return cousines
                .stream()
                .map(CousineResponse::from)
                .collect(Collectors.toList());
    }
}
