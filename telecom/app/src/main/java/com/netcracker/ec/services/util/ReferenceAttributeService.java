package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.services.console.Console;

import java.util.HashMap;
import java.util.Map;

public class ReferenceAttributeService {

    private final Console console = Console.getInstance();

    public String read(NcAttribute attr) {
        System.out.println("Please, enter reference.");

        Map<Integer, String> objectsMap = getObjectsMap();
        console.printAvailableOperations(objectsMap);

        Integer objectId = console.nextAvailableOperation(objectsMap.keySet());

        return objectId.toString();
    }

    private Map<Integer, String> getObjectsMap() {
        Map<Integer, String> objectsMap = new HashMap<>();
        return objectsMap;
    }
}
