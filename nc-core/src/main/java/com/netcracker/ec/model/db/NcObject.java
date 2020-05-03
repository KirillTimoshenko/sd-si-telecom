package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class NcObject extends NcEntity {
    private final NcObjectType objectType;
    private final Map<NcAttribute, String> params = new HashMap<>();
    @Setter
    private String description;

    public NcObject(NcObjectType objectType) {
        super();
        this.objectType = objectType;
    }

    public void setParam(NcAttribute attr, String object) {
        params.put(attr, object);
    }
}
