package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcAttributeService;
import com.netcracker.ec.services.db.Queries;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class NcAttributeServiceImpl implements NcAttributeService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    @Override
    public Set<NcAttribute> getAttributesByObjectTypeAndAttrSchema(Integer otId, Integer attrSchemaId) {
        String query = Queries.getQuery("get_attributes_by_ot_and_schema");
        return getNcAttributesByResultSet(DB_WORKER.executeSelectQuery(query, otId, attrSchemaId));
    }

    @Override
    public Set<NcAttribute> getAttributesByObjectType(Integer otId) {
        String query = Queries.getQuery("get_attributes_by_ot");
        return getNcAttributesByResultSet(DB_WORKER.executeSelectQuery(query, otId));
    }

    @Override
    public NcAttribute getAttributeById(Integer attrId) {
        String query = Queries.getQuery("get_attribute_by_id");
        return getNcAttributeByResultSet(DB_WORKER.executeSelectQuery(query, attrId));
    }

    @SneakyThrows
    private Set<NcAttribute> getNcAttributesByResultSet(ResultSet resultSet) {
        Set<NcAttribute> attributes = new HashSet<>();
        while (resultSet.next()) {
            attributes.add(createNcAttributeByResultSet(resultSet));
        }
        resultSet.close();
        return attributes;
    }

    @SneakyThrows
    private NcAttribute getNcAttributeByResultSet(ResultSet resultSet) {
        resultSet.next();
        NcAttribute attribute = createNcAttributeByResultSet(resultSet);
        resultSet.close();
        return attribute;
    }

    private NcAttribute createNcAttributeByResultSet(ResultSet resultSet) throws SQLException {
        return new NcAttribute(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getInt(3),
                new NcAttrTypeDefServiceImpl().getNcAttrTypeDefById(resultSet.getInt(4)));
    }
}
