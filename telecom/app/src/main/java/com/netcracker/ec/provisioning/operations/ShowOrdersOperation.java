package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectTypeServiceImpl;
import com.netcracker.ec.services.util.EntityIdManager;

import java.util.*;

public class ShowOrdersOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcObjectService ncObjectService;

    private final Console console = Console.getInstance();

    public ShowOrdersOperation() {
        this.ncObjectTypeService = new NcObjectTypeServiceImpl();
        this.ncObjectService = new NcObjectServiceImpl();
    }

    @Override
    public void execute() {
        console.printMessage("Please Select Operation.");

        Map<Integer, String> operationModifications = getOperationModificationsMap(
                "Show All Orders",
                "Show Orders Of A Specific Order Aim");
        console.printAvailableOperations(operationModifications);

        Integer operationModification = console.nextAvailableOperation(operationModifications.keySet());
        switch (operationModification) {
            case 1:
                showAllOrders();
                break;
            case 2:
                showOrderOfASpecificOrderAim();
                break;
            default:
                break;
        }
    }

    private void showAllOrders() {
        List<NcObject> objectsList = new ArrayList<>();

        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByPreviousParentId(Order.OBJECT_TYPE);

        objectTypesList.forEach(objectType -> objectsList.addAll(ncObjectService
                .getNcObjectsByObjectTypeId(objectType.getId())));

        printOrders(objectsList);
    }

    private void showOrderOfASpecificOrderAim() {
        console.printMessage("Please select Object Type:");
        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(Order.OBJECT_TYPE);
        console.printEntityList(objectTypesList);

        Integer orderAim = console.nextAvailableOperation(
                EntityIdManager.getIdSet(objectTypesList));

        console.printMessage("Please Select Modification:");

        Map<Integer, String> operationModifications = getOperationModificationsMap(
                "Show All Orders Of Such Order Aim",
                "Show Orders Of A Specific Object Type");
        console.printAvailableOperations(operationModifications);

        Integer operationModification = console.nextAvailableOperation(operationModifications.keySet());
        switch (operationModification) {
            case 1:
                showAllOrdersOfASpecificOrderAim(orderAim);
                break;
            case 2:
                showOrderOfASpecificObjectType(orderAim);
                break;
            default:
                break;
        }
    }

    private void showAllOrdersOfASpecificOrderAim(Integer orderAim) {
        List<NcObject> objectsList = new ArrayList<>();

        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(orderAim);

        objectTypesList.forEach(objectType -> objectsList.addAll(ncObjectService
                .getNcObjectsByObjectTypeId(objectType.getId())));

        printOrders(objectsList);
    }

    private void showOrderOfASpecificObjectType(Integer orderAim) {
        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(orderAim);
        console.printEntityList(objectTypesList);

        Integer objectTypeId = console.nextAvailableOperation(
                EntityIdManager.getIdSet(objectTypesList));

        List<NcObject> objectsList = ncObjectService.getNcObjectsByObjectTypeId(objectTypeId);

        printOrders(objectsList);
    }

    private void printOrders(List<NcObject> objects) {
        objects.forEach(object -> console.printOrderInfo(object, true));
    }

    private Map<Integer, String> getOperationModificationsMap(String... modifications) {
        Map<Integer, String> operationModifications = new HashMap<>();
        int modificationNumber = 1;
        for (String modification: modifications) {
            operationModifications.put(modificationNumber++, modification);
        }
        return operationModifications;
    }
}
