package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.OrderAim;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import lombok.Getter;
import lombok.Setter;

import static com.netcracker.ec.common.OmConstants.ATTR_PREVIOUS_ORDER;

@Getter
@Setter
public class DisconnectOrder extends Order {
    public static final Integer OBJECT_TYPE = 13;

    public DisconnectOrder(NcObjectType objectType, Order previousOrder) {
        super(objectType, OrderAim.DISCONNECT);
        setPreviousOrderId(previousOrder.getId());
    }

    public Integer getPreviousOrderId() {
        return getReferenceId(ATTR_PREVIOUS_ORDER);
    }

    public NcObject getPreviousOrder() {
        return new NcObjectServiceImpl().getNcObjectById(getPreviousOrderId());
    }

    public void setPreviousOrderId(Integer previousOrderId) {
        setReferenceId(ATTR_PREVIOUS_ORDER, previousOrderId);
    }

}
