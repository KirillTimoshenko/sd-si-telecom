package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@ToString
public enum AttributeType {
    TEXT(0, "Text"),
    NUMBER(2, "Number"),
    DECIMAL(3, "Decimal"),
    DATE(4, "Date"),
    LIST(6, "List"),
    REFERENCE(9, "Reference");

    private Integer id;
    private String name;

    public static AttributeType valueOf(Integer id) {
        return Arrays.stream(values())
                .filter(value -> value.getId().equals(id))
                .findFirst().orElse(null);
    }
}
