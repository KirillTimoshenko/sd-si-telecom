package com.netcracker.ec.model.db;

import lombok.Getter;

@Getter
public class NcListValue extends NcEntity {
    private NcAttrTypeDef attrTypeDef;

    public NcListValue(Integer id, String name, NcAttrTypeDef attrTypeDef) {
        super(id, name);
        this.attrTypeDef = attrTypeDef;
    }
}
