package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;

import java.util.List;

public class ReferenceAttributeService {
    private final NcObjectService ncObjectService;

    private final Console console = Console.getInstance();

    public ReferenceAttributeService() {
        this.ncObjectService = new NcObjectServiceImpl();
    }

    public String read(NcAttribute attr) {
        console.printMessage("Please, enter reference object id.");

        List<NcEntity> objectsList = ncObjectService.getNcObjectsAsEntitiesByObjectType(
                attr.getAttrTypeDef().getObjectType());
        console.printEntityList(objectsList);

        Integer objectId = console.nextAvailableOperation(EntityIdManager.getIdSet(objectsList));

        return objectId.toString();
    }
}
