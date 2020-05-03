package com.netcracker.ec.model.db;

import lombok.Getter;

@Getter
public class NcAttribute extends NcEntity {
    private NcAttrTypeDef attrTypeDef;
    private Integer attrSchemaId;

    public NcAttribute(Integer id, String name, Integer attrSchemaId, NcAttrTypeDef attrTypeDef) {
        super(id, name);
        this.attrSchemaId = attrSchemaId;
        this.attrTypeDef = attrTypeDef;
    }
}
