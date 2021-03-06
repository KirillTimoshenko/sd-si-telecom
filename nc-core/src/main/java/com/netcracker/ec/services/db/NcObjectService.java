package com.netcracker.ec.services.db;

import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;

import java.util.List;

public interface NcObjectService extends NcEntityService {
    NcObject getNcObjectById(Integer objectId);

    String getNcObjectNameById(Integer objectId);

    List<Integer> getObjectIdsByByObjectTypeId(Integer otId);

    List<NcObject> getNcObjectsByObjectTypeId(Integer objectTypeId);

    List<NcEntity> getNcObjectsAsEntitiesByObjectType(NcObjectType objectType);

    void insert(NcObject object);
}
