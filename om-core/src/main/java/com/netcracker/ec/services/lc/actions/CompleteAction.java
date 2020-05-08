package com.netcracker.ec.services.lc.actions;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.domain.enums.OrderStatus;
import com.netcracker.ec.model.domain.order.DisconnectOrder;
import com.netcracker.ec.model.domain.order.Order;

import static com.netcracker.ec.services.OpfUtils.getCurrentDate;

public class CompleteAction implements LifeCycleAction {
    @Override
    public void execute(NcObject object) {
        Order order = (Order) object;
        order.setCompletionDate(getCurrentDate().toString());

        if (object instanceof DisconnectOrder) {
            Order previousOrder = ((DisconnectOrder) object).getPreviousOrder();
            previousOrder.setStatus(OrderStatus.SUPERSEDED);
        }
    }
}
