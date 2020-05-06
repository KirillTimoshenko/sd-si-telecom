package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.Queries;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NcObjectServiceImpl extends NcEntityServiceImpl implements NcObjectService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    public NcObjectServiceImpl() {

    }

    @Override
    public NcObject getNcObjectById(Integer objectId) {
        String query = Queries.getQuery("get_object_by_id");
        return getNcObjectByResultSet(DB_WORKER.executeSelectQuery(query, objectId));
    }

    @Override
    public List<Integer> getObjectIdsByByObjectTypeId(Integer otId) {
        String query = Queries.getQuery("get_object_ids_by_parent_ot_desc");
        return DB_WORKER.getIdsByQuery(query, otId);
    }

    @Override
    public List<NcObject> getNcObjectsByObjectTypeId(Integer objectTypeId) {
        String query = Queries.getQuery("get_objects_by_ot");
        return getNcObjectsByResultSet(DB_WORKER.executeSelectQuery(query, objectTypeId));
    }

    @Override
    public List<NcEntity> getNcObjectsAsEntitiesByObjectType(NcObjectType objectType) {
        String query = Queries.getQuery("get_objects_as_entities_by_ot");
        return getNcEntitiesByResultSet(DB_WORKER.executeSelectQuery(query, objectType.getId()));
    }

    @Override
    public void insert(NcObject object) {
        String query = Queries.getQuery("insert_object");
        DB_WORKER.executeQuery(query, object.getId(), object.getName(), object.getObjectType().getId(), object.getDescription());
    }

    @SneakyThrows
    private NcObject getNcObjectByResultSet(ResultSet resultSet) {
        resultSet.next();
        NcObject object = createNcObjectByResultSet(resultSet);
        resultSet.close();
        return object;
    }

    @SneakyThrows
    private List<NcObject> getNcObjectsByResultSet(ResultSet resultSet) {
        List<NcObject> objectsList = new ArrayList<>();
        while (resultSet.next()) {
            objectsList.add(createNcObjectByResultSet(resultSet));
        }
        resultSet.close();
        return  objectsList;
    }

    private NcObject createNcObjectByResultSet(ResultSet resultSet) throws SQLException {
        return new NcObject(resultSet.getInt(1),
                resultSet.getString(2),
                new NcObjectTypeServiceImpl().getNcObjectTypeById(resultSet.getInt(3)),
                resultSet.getString(4));
    }
}
