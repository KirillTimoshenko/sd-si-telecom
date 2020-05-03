package com.netcracker.ec.services.lc.actions;

import com.netcracker.ec.model.db.NcObject;

public interface LifeCycleAction {
    void execute(NcObject object);
}
