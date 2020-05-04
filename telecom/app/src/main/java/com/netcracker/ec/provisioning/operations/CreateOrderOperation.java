package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcAttributeService;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcAttributeServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectTypeServiceImpl;
import com.netcracker.ec.services.util.AttributeValueManager;

import java.util.*;

public class CreateOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcAttributeService ncAttributeService;
    private final NcObjectService ncObjectService;
    private final AttributeValueManager attributeValueManager;

    private final Console console = Console.getInstance();

    public CreateOrderOperation() {
        this.ncObjectTypeService = new NcObjectTypeServiceImpl();
        this.ncAttributeService = new NcAttributeServiceImpl();
        this.ncObjectService = new NcObjectServiceImpl();
        this.attributeValueManager = new AttributeValueManager(console.getScanner());
}

    @Override
    public void execute() {
        System.out.println("Please Select Object Type.");

        Map<Integer, String> orderObjectTypeMap = getOrdersObjectTypeNameMap();
        console.printAvailableOperations(orderObjectTypeMap);

        Integer objectTypeId = console.nextAvailableOperation(orderObjectTypeMap.keySet());

        Set<NcAttribute> attributes = ncAttributeService
                .getAttributesByObjectTypeAndAttrSchema(objectTypeId, 40);

        Order order = new Order(ncObjectTypeService.getNcObjectTypeById(objectTypeId));

        attributeValueManager.readOrderAttributes(order, attributes);

        if (console.getSaveDialogueAnswer()) {
            addOrder(order);
            addOrderParams(order);
            console.printOrderInfo(order);
        }
    }

    private void addOrder(Order order) {
        //ncObjectService.insert(order);
    }

    private void addOrderParams(Order order) {
        attributeValueManager.mergerOrderAttributes(order);
    }

    private Map<Integer, String> getOrdersObjectTypeNameMap() {
        List<NcObjectType> objectTypesList = ncObjectTypeService.getObjectTypesByParentId(2);
        Map<Integer, String> objectTypesMap = new HashMap<>();
        objectTypesList.forEach(objectType -> objectTypesMap
                .put(objectType.getId(), objectType.getName()));
        return objectTypesMap;
    }
}
