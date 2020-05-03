package com.netcracker.ec.services.db;

import com.netcracker.ec.model.db.NcAttribute;

import java.util.Set;

public interface NcAttributeService {
    Set<NcAttribute> getAttributesByObjectTypeAndAttrSchema(Integer otId, Integer attrSchemaId);

    Set<NcAttribute> getAttributesByObjectType(Integer otId);

    NcAttribute getAttributeById(Integer attrId);
}
