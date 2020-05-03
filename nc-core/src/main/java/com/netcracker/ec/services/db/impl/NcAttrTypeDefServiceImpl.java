package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcAttrTypeDef;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.AttributeType;
import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcAttrTypeDefService;
import com.netcracker.ec.services.db.Queries;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class NcAttrTypeDefServiceImpl implements NcAttrTypeDefService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    @Override
    public NcAttrTypeDef getNcAttrTypeDefById(Integer attrTypeDefId) {
        String query = Queries.getQuery("get_attr_type_def_by_id");
        return createAttrTypeDefByResultSet(DB_WORKER.executeSelectQuery(query, attrTypeDefId));
    }

    @SneakyThrows
    private NcAttrTypeDef createAttrTypeDefByResultSet(ResultSet resultSet) {
        resultSet.next();
        AttributeType attributeType = AttributeType.valueOf(resultSet.getInt(2));
        NcObjectType objectType = AttributeType.REFERENCE == attributeType
                ? new NcObjectTypeServiceImpl().getNcObjectTypeById(resultSet.getInt(3))
                : null;
        int attrTypeDefId = resultSet.getInt(1);
        resultSet.close();
        return new NcAttrTypeDef(attrTypeDefId, attributeType, objectType);
    }
}
