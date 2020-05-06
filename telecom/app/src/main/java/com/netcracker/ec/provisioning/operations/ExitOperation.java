package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.Application;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.DbWorker;

public class ExitOperation implements Operation {

    @Override
    public void execute() {
        Console.getInstance().close();
        DbWorker.getInstance().close();
        Application.setIsApplicationRunning(false);
    }
}
