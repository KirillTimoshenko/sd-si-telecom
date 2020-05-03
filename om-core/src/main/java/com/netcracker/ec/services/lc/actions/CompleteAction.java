package com.netcracker.ec.services.lc.actions;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.domain.order.Order;

import java.util.Calendar;
import java.util.Date;

public class CompleteAction implements LifeCycleAction {
    @Override
    public void execute(NcObject object) {
        Order order = (Order) object;
        Date date = Calendar.getInstance().getTime();
        order.setCompletionDate(date.toString());
    }
}
