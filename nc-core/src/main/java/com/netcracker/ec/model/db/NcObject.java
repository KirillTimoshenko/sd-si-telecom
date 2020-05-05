package com.netcracker.ec.model.db;

import com.netcracker.ec.services.db.impl.NcParamsServiceImpl;
import com.netcracker.ec.services.db.impl.NcReferencesServiceImpl;
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

    public void setReferenceId(Integer attrId, Integer refId) {
        new NcReferencesServiceImpl().mergeReference(attrId, getId(), refId);
    }

    public void setListValueId(Integer attrId, Integer lvId) {
        new NcParamsServiceImpl().mergeListValue(getId(), attrId, lvId);
    }

    public void setStringValue(Integer attrId, String string) {
        new NcParamsServiceImpl().mergeValue(getId(), attrId, string);
    }

    public Integer getReferenceId(Integer attrId) {
        return new NcReferencesServiceImpl().selectReference(getId(), attrId);
    }

    public Integer getListValueId(Integer attrId) {
        return new NcParamsServiceImpl().selectListValueId(getId(), attrId);
    }

    public String getStringValue(Integer attrId) {
        return new NcParamsServiceImpl().selectStringValue(getId(), attrId);
    }
}
