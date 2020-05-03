package com.netcracker.ec.services.db;

public interface NcParamsService {
    void mergeValue(Integer objectId, Integer attrId, String value);

    void mergeListValue(Integer objectId, Integer attrId, Integer listValueId);

    String selectStringValue(Integer objectId, Integer attrId);

    Integer selectListValueId(Integer objectId, Integer attrId);
}
