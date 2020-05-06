package com.netcracker.ec.services.db;

import com.netcracker.ec.model.db.NcEntity;

import java.util.List;

public interface NcListValueService extends NcEntityService {
    String getNcListValueNameById(Integer listValueId);

    List<NcEntity> getNcListValuesAsEntitiesByNcAttrTypeDefId(Integer attrTypeDefId);
}
