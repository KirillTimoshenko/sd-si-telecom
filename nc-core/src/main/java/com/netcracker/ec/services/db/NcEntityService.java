package com.netcracker.ec.services.db;

import com.netcracker.ec.model.db.NcEntity;

import java.sql.ResultSet;
import java.util.List;

public interface NcEntityService {
    List<NcEntity> getNcEntitiesByResultSet(ResultSet resultSet);
}
