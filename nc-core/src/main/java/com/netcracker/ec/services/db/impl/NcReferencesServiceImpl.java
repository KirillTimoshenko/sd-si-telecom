package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcReferencesService;
import com.netcracker.ec.services.db.Queries;

public class NcReferencesServiceImpl implements NcReferencesService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    public NcReferencesServiceImpl() {

    }

    @Override
    public void mergeReference(Integer attrId, Integer objectId, Integer refId) {
        String query = Queries.getQuery("merge_into_nc_references");
        DB_WORKER.executeQuery(query, objectId, attrId, refId);
    }

    @Override
    public Integer selectReference(Integer objectId, Integer attrId) {
        String query = Queries.getQuery("get_reference_id");
        return DB_WORKER.getIdByQuery(query, objectId, attrId);
    }
}
