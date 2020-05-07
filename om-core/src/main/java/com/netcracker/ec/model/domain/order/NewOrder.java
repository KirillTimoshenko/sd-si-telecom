package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.OrderAim;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrder extends Order {
    public static final Integer OBJECT_TYPE = 11;

    public NewOrder(NcObjectType objectType) {
        super(objectType, OrderAim.NEW);
    }
}
