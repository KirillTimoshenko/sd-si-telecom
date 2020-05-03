package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    NA(32),
    ENTERING(33),
    READY_FOR_PROCESSING(34),
    PROCESSING(35),
    COMPLETED(36),
    CANCELED(37);

    private final Integer lvId;

    public static OrderStatus valueOf(Integer id) {
        return Arrays.stream(values())
                .filter(sts -> sts.lvId.equals(id))
                .findFirst().orElse(null);
    }
}
