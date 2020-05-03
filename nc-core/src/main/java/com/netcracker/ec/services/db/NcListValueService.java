package com.netcracker.ec.services.db;

import com.netcracker.ec.model.db.NcEntity;

import java.util.List;

public interface NcListValueService extends NcEntityService {
    List<NcEntity> getNcListValuesAsEntitiesByNcAttrTypeDefId(Integer attrTypeDefId);
}
