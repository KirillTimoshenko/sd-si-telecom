package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcListValueService;
import com.netcracker.ec.services.db.Queries;

import java.util.List;

public class NcListValueServiceImpl extends NcEntityServiceImpl implements NcListValueService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    public NcListValueServiceImpl() {

    }

    @Override
    public List<NcEntity> getNcListValuesAsEntitiesByNcAttrTypeDefId(Integer attrTypeDefId) {
        String query = Queries.getQuery("get_list_values_by_attr_type_def");
        return getNcEntitiesByResultSet(DB_WORKER.executeSelectQuery(query, attrTypeDefId));
    }
}
