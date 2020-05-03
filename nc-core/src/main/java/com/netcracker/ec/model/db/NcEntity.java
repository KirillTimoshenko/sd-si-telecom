package com.netcracker.ec.model.db;

import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.impl.NcParamsServiceImpl;
import com.netcracker.ec.services.db.impl.NcReferencesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class NcEntity {
    private final Integer id;
    @Setter()
    private String name;

    public NcEntity() {
        this.id = DbWorker.getInstance().generateId();
    }

    public String toFormattedOutput() {
        return id + " - " + name;
    }

    public void setReferenceId(Integer attrId, Integer refId) {
        new NcReferencesServiceImpl().mergeReference(attrId, id, refId);
    }

    public void setListValueId(Integer attrId, Integer lvId) {
        new NcParamsServiceImpl().mergeListValue(id, attrId, lvId);
    }

    public void setStringValue(Integer attrId, String string) {
        new NcParamsServiceImpl().mergeValue(id, attrId, string);
    }

    public Integer getReferenceId(Integer attrId) {
        return new NcReferencesServiceImpl().selectReference(id, attrId);
    }

    public Integer getListValueId(Integer attrId) {
        return new NcParamsServiceImpl().selectListValueId(id, attrId);
    }

    public String getStringValue(Integer attrId) {
        return new NcParamsServiceImpl().selectStringValue(id, attrId);
    }
}
