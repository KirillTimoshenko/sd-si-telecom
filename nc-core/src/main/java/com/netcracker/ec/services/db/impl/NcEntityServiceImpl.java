package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.services.db.DbWorker;
import com.netcracker.ec.services.db.NcEntityService;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NcEntityServiceImpl implements NcEntityService {
    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    @SneakyThrows
    @Override
    public List<NcEntity> getNcEntitiesByResultSet(ResultSet resultSet) {
        List<NcEntity> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(new NcEntity(resultSet.getInt(1), resultSet.getString(2)));
        }
        resultSet.close();
        return entities;
    }
}
