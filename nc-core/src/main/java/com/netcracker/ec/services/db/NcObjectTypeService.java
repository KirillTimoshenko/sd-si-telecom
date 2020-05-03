package com.netcracker.ec.services.db;

import com.netcracker.ec.model.db.NcObjectType;

import java.util.List;

public interface NcObjectTypeService {
    NcObjectType getNcObjectTypeById(Integer id);

    List<NcObjectType> getObjectTypesByParentId(Integer parentOtId);
}
