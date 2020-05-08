package com.netcracker.ec.model.db;

import com.netcracker.ec.services.db.DbWorker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NcEntity {
    private Integer id;
    @Setter
    private String name;

    public NcEntity(String name) {
        id = DbWorker.getInstance().generateId();
        this.name = name;
    }

    public String toFormattedOutput() {
        return id + " - " + name;
    }

}
