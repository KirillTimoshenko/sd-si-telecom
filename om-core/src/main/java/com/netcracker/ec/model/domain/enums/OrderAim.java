package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderAim {
    NEW(412),
    MODIFY(413),
    DISCONNECT(414);

    private final Integer lvId;

    public static OrderAim valueOf(Integer id) {
        return Arrays.stream(values())
                .filter(sts -> sts.lvId.equals(id))
                .findFirst().orElse(null);
    }
}
