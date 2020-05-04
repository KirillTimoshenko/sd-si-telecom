package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReferenceAttributeService {
    private final NcObjectService ncObjectService;

    private final Console console = Console.getInstance();

    public ReferenceAttributeService() {
        this.ncObjectService = new NcObjectServiceImpl();
    }

    public String read(NcAttribute attr) {
        console.printMessage("Please, enter reference.");

        Map<Integer, String> objectsMap = getObjectsMap(attr);
        console.printAvailableOperations(objectsMap);

        Integer objectId = console.nextAvailableOperation(objectsMap.keySet());

        return objectId.toString();
    }

    private Map<Integer, String> getObjectsMap(NcAttribute attr) {
        List<NcEntity> list = ncObjectService.getNcObjectsAsEntitiesByObjectType(
                attr.getAttrTypeDef().getObjectType());

        Map<Integer, String> objectsMap = new HashMap<>();
        list.forEach(obj -> objectsMap.put(obj.getId(), obj.getName()));
        return objectsMap;
    }
}
