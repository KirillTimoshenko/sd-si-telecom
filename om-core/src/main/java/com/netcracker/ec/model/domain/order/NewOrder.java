package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.enums.OrderAim;
import com.netcracker.ec.services.omresolver.annotations.ObjectType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ObjectType(objectTypeId = NewOrder.OBJECT_TYPE)
@NoArgsConstructor
public class NewOrder extends Order {
    public static final int OBJECT_TYPE = 11;

    public NewOrder(NcObjectType objectType) {
        super(objectType, OrderAim.NEW);
    }
}
