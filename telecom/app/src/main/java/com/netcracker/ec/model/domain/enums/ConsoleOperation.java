package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ConsoleOperation {
    EXIT("0", "Exit"),
    SHOW_ORDERS("1", "Show orders"),
    CREATE_ORDER("2", "Create order");

    private String id;
    private String name;

    public static ConsoleOperation getOperationById(String id) {
        return Arrays.stream(values())
                .filter(value -> value.getId().equals(id))
                .findFirst().orElse(null);
    }

    public static boolean contains(String id) {
        return Arrays.stream(ConsoleOperation.values()).anyMatch(op -> op.getId().equals(id));
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
