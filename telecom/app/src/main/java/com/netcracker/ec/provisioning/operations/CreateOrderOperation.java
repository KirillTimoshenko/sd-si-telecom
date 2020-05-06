package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcAttributeService;
import com.netcracker.ec.services.db.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcAttributeServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectTypeServiceImpl;
import com.netcracker.ec.services.util.AttributeValueManager;
import com.netcracker.ec.services.util.EntityIdManager;

import java.util.*;

import static com.netcracker.ec.common.TelecomConstants.ATTR_SCHEMA_ID;
import static com.netcracker.ec.common.TelecomConstants.ABSTRACT_ORDER_OBJECT_TYPE;

public class CreateOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcAttributeService ncAttributeService;
    private final AttributeValueManager attributeValueManager;

    private final Console console = Console.getInstance();

    public CreateOrderOperation() {
        this.ncObjectTypeService = new NcObjectTypeServiceImpl();
        this.ncAttributeService = new NcAttributeServiceImpl();
        this.attributeValueManager = new AttributeValueManager(console.getScanner());
}

    @Override
    public void execute() {
        console.printMessage("Please Select Object Type.");

        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(ABSTRACT_ORDER_OBJECT_TYPE);
        console.printEntityList(objectTypesList);

        Integer objectTypeId = console.nextAvailableOperation(EntityIdManager.getIdSet(objectTypesList));

        Set<NcAttribute> attributes = ncAttributeService
                .getAttributesByObjectTypeAndAttrSchema(objectTypeId, ATTR_SCHEMA_ID);

        Order order = new Order(ncObjectTypeService.getNcObjectTypeById(objectTypeId));

        attributeValueManager.readOrderAttributes(order, attributes);

        if (console.getSaveDialogueAnswer()) {
            addOrderParams(order);
            console.printOrderInfo(order);
        }
    }

    private void addOrderParams(Order order) {
        attributeValueManager.mergerOrderAttributes(order);
    }

    private Map<Integer, String> getOrdersObjectTypeNameMap() {
        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentId(ABSTRACT_ORDER_OBJECT_TYPE);
        Map<Integer, String> objectTypesMap = new HashMap<>();
        objectTypesList.forEach(objectType -> objectTypesMap
                .put(objectType.getId(), objectType.getName()));
        return objectTypesMap;
    }
}
