package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcAttributeService;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcAttributeServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectTypeServiceImpl;
import com.netcracker.ec.services.util.AttributeValueManager;
import com.netcracker.ec.services.util.EntityIdManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netcracker.ec.common.TelecomConstants.NEW_ORDER_OBJECT_TYPE;
import static com.netcracker.ec.common.TelecomConstants.TELECOM_OM_SCHEMA_ID;

public class ShowOrdersOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcObjectService ncObjectService;
    private final NcAttributeService ncAttributeService;
    private final AttributeValueManager attributeValueManager;

    private final Console console = Console.getInstance();

    public ShowOrdersOperation() {
        this.ncObjectTypeService = new NcObjectTypeServiceImpl();
        this.ncObjectService = new NcObjectServiceImpl();
        this.ncAttributeService = new NcAttributeServiceImpl();
        this.attributeValueManager = new AttributeValueManager(console.getScanner());
    }

    @Override
    public void execute() {
        console.printMessage("Please Select Operation.");

        Map<Integer, String> operationModifications = getOperationModificationsMap();
        console.printAvailableOperations(operationModifications);

        Integer operationModification = console.nextAvailableOperation(operationModifications.keySet());
        switch (operationModification) {
            case 1:
                showAllOrders();
                break;
            case 2:
                showOrderOfASpecificObjectType();
                break;
            default:
                break;
        }
    }

    private void showAllOrders() {
        List<NcObject> objectsList = new ArrayList<>();

        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(NEW_ORDER_OBJECT_TYPE);

        objectTypesList.forEach(objectType -> objectsList.addAll(ncObjectService
                .getNcObjectsByObjectTypeId(objectType.getId())));

        initOrdersParameters(objectsList);
        printOrders(objectsList);
    }

    private void showOrderOfASpecificObjectType() {
        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(NEW_ORDER_OBJECT_TYPE);
        console.printEntityList(objectTypesList);

        Integer objectTypeId = console.nextAvailableOperation(
                EntityIdManager.getIdSet(objectTypesList));

        List<NcObject> objectsList = ncObjectService.getNcObjectsByObjectTypeId(objectTypeId);

        initOrdersParameters(objectsList);
        printOrders(objectsList);
    }

    private void initOrdersParameters(List<NcObject> objects) {
        objects.forEach(object -> attributeValueManager
                .initOrderAttributes(object, ncAttributeService
                        .getAttributesByObjectTypeAndAttrSchema(object.getObjectType().getId(),
                                TELECOM_OM_SCHEMA_ID)));
    }

    private void printOrders(List<NcObject> objects) {
        objects.forEach(console::printOrderInfo);
    }

    private Map<Integer, String> getOperationModificationsMap() {
        Map<Integer, String> operationModifications = new HashMap<>();
        operationModifications.put(1, "Show All Orders");
        operationModifications.put(2, "Show Orders Of A Specific Object Type");
        return operationModifications;
    }
}
