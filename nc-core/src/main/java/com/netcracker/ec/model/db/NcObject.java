package com.netcracker.ec.model.db;

import com.netcracker.ec.services.db.impl.NcListValueServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.db.impl.NcParamsServiceImpl;
import com.netcracker.ec.services.db.impl.NcReferencesServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static com.netcracker.ec.common.CommonConstants.*;

@Getter
@NoArgsConstructor
public class NcObject extends NcEntity {
    private static final Integer ATTR_CREATED_WHEN = 611;

    private NcObjectType objectType;
    private final Map<NcAttribute, String> params = new HashMap<>();
    @Setter
    private String description;

    public NcObject(NcObjectType objectType) {
        super(generateObjectName(objectType));
        this.objectType = objectType;
    }

    public NcObject(Integer id, String name, NcObjectType objectType, String description) {
        super(id, name);
        this.objectType = objectType;
        this.description = description;
    }

    public void setParam(NcAttribute attr, String object) {
        params.put(attr, object);
    }

    public String getCreatedWhen() {
        return getStringValue(ATTR_CREATED_WHEN);
    }

    public void setCreatedWhen(String createdWhen) {
        setStringValue(ATTR_CREATED_WHEN, createdWhen);
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

    public String getReferenceValue(Integer attrId) {
        return new NcObjectServiceImpl().getNcObjectById(getReferenceId(attrId)).getName();
    }

    public String getListValue(Integer attrId) {
        return new NcListValueServiceImpl().getNcListValueNameById(getListValueId(attrId));
    }

    private static String generateObjectName(NcObjectType objectType) {
        String objectTypeName = objectType.getName();
        return objectTypeName.contains(STR_OBJECT_TYPE)
                ? objectTypeName.replace(STR_OBJECT_TYPE, STR_EMPTY)
                : objectTypeName.contains(STR_TYPE)
                        ? objectTypeName.replace(STR_TYPE, STR_EMPTY)
                        : objectTypeName + STR_SPACE;
    }
}
