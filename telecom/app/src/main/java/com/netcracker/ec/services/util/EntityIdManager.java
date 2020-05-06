package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class EntityIdManager {
    private EntityIdManager() {
    }

    public static Set<Integer> getIdSet(List<? extends NcEntity> entityList) {
        Set<Integer> idSet = new HashSet<>();
        entityList.forEach(entity -> idSet.add(entity.getId()));
        return idSet;
    }
}
