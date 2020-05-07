package com.netcracker.ec.services;

import com.netcracker.ec.model.domain.order.Order;

import java.util.Calendar;
import java.util.Date;

import static com.netcracker.ec.common.OmConstants.STR_EMPTY;
import static com.netcracker.ec.common.OmConstants.STR_HASH;
import static com.netcracker.ec.common.OmConstants.STR_OBJECT_TYPE;
import static com.netcracker.ec.common.OmConstants.STR_SPACE;
import static com.netcracker.ec.common.OmConstants.STR_TYPE;

public class OpfUtils {
    public static void generateOrderName(Order order) {
        String objectTypeName = order.getObjectType().getName();
        String name = objectTypeName.contains(STR_OBJECT_TYPE)
                ? objectTypeName.replace(STR_OBJECT_TYPE, STR_EMPTY)
                : objectTypeName.contains(STR_TYPE)
                        ? objectTypeName.replace(STR_TYPE, STR_EMPTY)
                        : objectTypeName + STR_SPACE;
        order.setName(name + STR_HASH + order.getId());
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }
}
