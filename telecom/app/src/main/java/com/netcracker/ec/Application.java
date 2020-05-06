package com.netcracker.ec;

import com.netcracker.ec.services.console.Console;

public class Application {
    private static boolean isApplicationRunning = false;

    public void run() throws Exception {

        if (!isApplicationRunning) {
            isApplicationRunning = true;
        }

        Console console = Console.getInstance();

        while (isApplicationRunning) {
            console.getNextOperation().execute();
        }
    }

    public static void setIsApplicationRunning(boolean isRunning) {
        isApplicationRunning = isRunning;
    }
}
