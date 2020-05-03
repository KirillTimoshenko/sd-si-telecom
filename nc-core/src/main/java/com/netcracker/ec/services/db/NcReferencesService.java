package com.netcracker.ec.services.db;

public interface NcReferencesService {
    void mergeReference(Integer attrId, Integer objectId, Integer refId);

    Integer selectReference(Integer objectId, Integer attrId);
}
