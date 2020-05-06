package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class EntityIdManager {
    private EntityIdManager() {
    }

    public static Set<Integer> getIdSet(List<? extends NcEntity> entityList) {
        return entityList.stream().map(NcEntity::getId).collect(Collectors.toSet());
    }
}
