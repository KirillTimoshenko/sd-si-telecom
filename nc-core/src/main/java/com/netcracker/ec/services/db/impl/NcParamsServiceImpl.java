package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcParamsService;
import com.netcracker.ec.services.db.Queries;

public class NcParamsServiceImpl implements NcParamsService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    public NcParamsServiceImpl() {

    }

    @Override
    public void mergeValue(Integer objectId, Integer attrId, String value) {
        mergeIntoNcParams(objectId, attrId, null, value);
    }

    @Override
    public void mergeListValue(Integer objectId, Integer attrId, Integer listValueId) {
        mergeIntoNcParams(objectId, attrId, listValueId, null);
    }

    public String selectStringValue(Integer objectId, Integer attrId) {
        String query = Queries.getQuery("get_value_param");
        return DB_WORKER.getStringValueByQuery(query, objectId, attrId);
    }

    public Integer selectListValueId(Integer objectId, Integer attrId) {
        String query = Queries.getQuery("get_list_value_id_param");
        return DB_WORKER.getIdByQuery(query, objectId, attrId);
    }

    private void mergeIntoNcParams(Integer objectId, Integer attrId, Integer listValueId, String value) {
        String query = Queries.getQuery("merge_into_nc_params");
        DB_WORKER.executeQuery(query, objectId, attrId, listValueId, value);
    }
}
