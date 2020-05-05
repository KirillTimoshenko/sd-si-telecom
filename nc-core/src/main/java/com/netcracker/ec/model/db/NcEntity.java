package com.netcracker.ec.model.db;

import com.netcracker.ec.services.db.DbWorker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class NcEntity {
    private final Integer id;
    @Setter()
    private String name;

    public NcEntity() {
        this.id = DbWorker.getInstance().generateId();
    }

    public String toFormattedOutput() {
        return id + " - " + name;
    }

}
