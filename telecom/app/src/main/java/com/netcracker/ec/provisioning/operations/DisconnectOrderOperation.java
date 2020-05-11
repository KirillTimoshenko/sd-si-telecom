package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.OrderStatus;
import com.netcracker.ec.model.domain.order.DisconnectOrder;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcAttributeService;
import com.netcracker.ec.services.db.NcObjectService;
import com.netcracker.ec.services.db.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcAttributeServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.db.impl.NcObjectTypeServiceImpl;
import com.netcracker.ec.services.omresolver.OrderResolverFactory;
import com.netcracker.ec.services.util.AttributeValueManager;
import com.netcracker.ec.services.util.EntityIdManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.netcracker.ec.common.OmConstants.ATTR_ORDER_STATUS;
import static com.netcracker.ec.common.OmConstants.MODIFY_ORDER_OBJECT_TYPE;
import static com.netcracker.ec.common.OmConstants.NEW_ORDER_OBJECT_TYPE;
import static com.netcracker.ec.common.OmConstants.TELECOM_OM_SCHEMA_ID;

public class DisconnectOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcObjectService ncObjectService;
    private final NcAttributeService ncAttributeService;
    private final AttributeValueManager attributeValueManager;

    private final Console console = Console.getInstance();
    private final OrderResolverFactory orderFactory = OrderResolverFactory.getInstance();

    public DisconnectOrderOperation() {
        this.ncObjectTypeService = new NcObjectTypeServiceImpl();
        this.ncObjectService = new NcObjectServiceImpl();
        this.ncAttributeService =  new NcAttributeServiceImpl();
        this.attributeValueManager = new AttributeValueManager(console.getScanner());
    }

    public void execute() {
        console.printMessage("Please Select Order ID.");

        List<NcObject> list = getNcObjectsWithCompletedStatus();
        console.printEntityList(list);

        Integer objectId = console.nextAvailableOperation(EntityIdManager.getIdSet(list));

        Order previousOrder = orderFactory.findOrderById(objectId);

        Order disconnectOrder = new DisconnectOrder(previousOrder.getObjectType(), previousOrder);

        Set<NcAttribute> attributes = ncAttributeService
                .getAttributesByObjectTypeAndAttrSchema(
                        disconnectOrder.getObjectType().getId(),
                        TELECOM_OM_SCHEMA_ID);

        attributeValueManager.readOrderAttributes(disconnectOrder, attributes);

        if (console.getSaveDialogueAnswer()) {
            disconnectOrder.save();
            console.printOrderInfo(disconnectOrder);
        }
    }

    private List<NcObject> getNcObjectsWithCompletedStatus() {
        List<NcObject> objects = new ArrayList<>();

        List<NcObjectType> objectTypesList = ncObjectTypeService
                .getObjectTypesByParentIds(NEW_ORDER_OBJECT_TYPE, MODIFY_ORDER_OBJECT_TYPE);

        objectTypesList.forEach(objectType -> objects.addAll(ncObjectService
                .getNcObjectsByObjectTypeId(objectType.getId())));

        return objects.stream()
                .filter(object -> OrderStatus.COMPLETED.getLvId()
                        .equals(object.getListValueId(ATTR_ORDER_STATUS)))
                .collect(Collectors.toList());
    }
}
