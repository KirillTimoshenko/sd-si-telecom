package com.netcracker.ec.services;

import com.netcracker.ec.model.domain.order.Order;

import java.util.Calendar;
import java.util.Date;

import static com.netcracker.ec.common.CommonConstants.STR_HASH;

public class OpfUtils {
    public static void generateOrderName(Order order) {
        order.setName(order.getName() + STR_HASH + order.getId());
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
