package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.LifeCycleEvent;
import com.netcracker.ec.model.domain.enums.OrderStatus;
import com.netcracker.ec.services.OpfUtils;
import com.netcracker.ec.services.db.impl.NcObjectServiceImpl;
import com.netcracker.ec.services.lc.LifeCycleManager;
import lombok.Getter;
import lombok.Setter;

import static com.netcracker.ec.common.OmConstants.ATTR_ORDER_COMPLETION_DATE;
import static com.netcracker.ec.common.OmConstants.ATTR_ORDER_STATUS;

@Getter
@Setter
public class Order extends NcObject {
    public Order(NcObjectType objectType) {
        super(objectType);
        OpfUtils.generateOrderName(this);
        new NcObjectServiceImpl().insert(this);
        setStatus(OrderStatus.NA);
    }

    public void save() {
        LifeCycleManager.sendEvent(LifeCycleEvent.INIT, this);
        LifeCycleManager.sendEvent(LifeCycleEvent.CREATE, this);
        LifeCycleManager.sendEvent(LifeCycleEvent.START_PROCESSING, this);
        LifeCycleManager.sendEvent(LifeCycleEvent.COMPLETE, this);
    }

    public OrderStatus getStatus() {
        return OrderStatus.valueOf(getListValueId(ATTR_ORDER_STATUS));
    }

    public void setStatus(OrderStatus status) {
        setListValueId(ATTR_ORDER_STATUS, status.getLvId());
    }

    public String getCompletionDate() {
        return getStringValue(ATTR_ORDER_COMPLETION_DATE);
    }

    public void setCompletionDate(String completionDate) {
        setStringValue(ATTR_ORDER_COMPLETION_DATE, completionDate);
    }
}
