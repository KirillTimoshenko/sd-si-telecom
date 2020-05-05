package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcObjectTypeService;
import com.netcracker.ec.services.db.Queries;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NcObjectTypeServiceImpl implements NcObjectTypeService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    public NcObjectTypeServiceImpl() {

    }

    @SneakyThrows
    @Override
    public NcObjectType getNcObjectTypeById(Integer otId) {
        String query = Queries.getQuery("get_object_type_by_id");
        ResultSet resultSet = DB_WORKER.executeSelectQuery(query, otId);
        resultSet.next();
        NcObjectType ncObjectType = createNcObjectTypeByResultSet(resultSet);
        resultSet.close();
        return ncObjectType;
    }

    @SneakyThrows
    @Override
    public List<NcObjectType> getObjectTypesByParentId(Integer parentOtId) {
        String query = Queries.getQuery("get_object_types_by_parent_id");
        ResultSet resultSet = DB_WORKER.executeSelectQuery(query, parentOtId);
        List<NcObjectType> objectTypes = new ArrayList<>();
        while (resultSet.next()) {
            objectTypes.add(createNcObjectTypeByResultSet(resultSet));
        }
        resultSet.close();
        return objectTypes;
    }

    private NcObjectType createNcObjectTypeByResultSet(ResultSet resultSet) throws SQLException {
        return new NcObjectType(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
    }
}
