package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.OrderAim;
import com.netcracker.ec.services.omresolver.OrderResolverFactory;
import com.netcracker.ec.services.omresolver.annotations.ObjectType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.netcracker.ec.common.OmConstants.ATTR_PREVIOUS_ORDER;

@Getter
@Setter
@ObjectType(objectTypeId = DisconnectOrder.OBJECT_TYPE)
@NoArgsConstructor
public class DisconnectOrder extends Order {
    public static final int OBJECT_TYPE = 13;

    public DisconnectOrder(NcObjectType objectType, Order previousOrder) {
        super(objectType, OrderAim.DISCONNECT);
        setPreviousOrderId(previousOrder.getId());
    }

    public Integer getPreviousOrderId() {
        return getReferenceId(ATTR_PREVIOUS_ORDER);
    }

    public Order getPreviousOrder() {
        return OrderResolverFactory.getInstance().findOrderById(getPreviousOrderId());
    }

    public void setPreviousOrderId(Integer previousOrderId) {
        setReferenceId(ATTR_PREVIOUS_ORDER, previousOrderId);
    }

}
