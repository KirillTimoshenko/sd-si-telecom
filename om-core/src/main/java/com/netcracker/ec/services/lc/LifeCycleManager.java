package com.netcracker.ec.services.lc;

import com.netcracker.ec.exceptions.LifeCycleException;
import com.netcracker.ec.model.domain.enums.LifeCycleEvent;
import com.netcracker.ec.model.domain.enums.OrderStatus;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.lc.actions.LifeCycleAction;

public class LifeCycleManager {
    public static void sendEvent(LifeCycleEvent event, Order order) {
        OrderStatus status = order.getStatus();
        if (event.getInitialStatus() != status) {
            throw new LifeCycleException("LifeCycle Event '" + event + "' doesn't correspond to Initial OrderStatus '" + status + "'!");
        }

        try {
            LifeCycleAction lcAction = event.getActionClass().newInstance();
            lcAction.execute(order);
            order.setStatus(event.getFinalStatus());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new LifeCycleException("Cannot instantiate LifeCycle Action for event: '" + event + "'!");
        }
    }
}
