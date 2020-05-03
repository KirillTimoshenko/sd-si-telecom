package com.netcracker.ec.model.domain.enums;

import com.netcracker.ec.services.lc.actions.LifeCycleAction;
import com.netcracker.ec.services.lc.actions.CompleteAction;
import com.netcracker.ec.services.lc.actions.CreateAction;
import com.netcracker.ec.services.lc.actions.InitAction;
import com.netcracker.ec.services.lc.actions.StartAction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LifeCycleEvent {
    INIT(InitAction.class, OrderStatus.NA, OrderStatus.ENTERING),
    CREATE(CreateAction.class, OrderStatus.ENTERING, OrderStatus.READY_FOR_PROCESSING),
    START_PROCESSING(StartAction.class, OrderStatus.READY_FOR_PROCESSING, OrderStatus.PROCESSING),
    COMPLETE(CompleteAction.class, OrderStatus.PROCESSING, OrderStatus.COMPLETED);

    private final Class<? extends LifeCycleAction> actionClass;
    private final OrderStatus initialStatus;
    private final OrderStatus finalStatus;
}
