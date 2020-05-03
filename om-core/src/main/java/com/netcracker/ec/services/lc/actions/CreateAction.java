package com.netcracker.ec.services.lc.actions;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.domain.order.Order;

import java.util.Map;

public class CreateAction implements LifeCycleAction {
    @Override
    public void execute(NcObject object) {
        Order order = (Order) object;
        for (Map.Entry<NcAttribute, String> param : order.getParams().entrySet()) {
            NcAttribute attribute = param.getKey();
            switch (attribute.getAttrTypeDef().getType()) {
                case LIST:
                    order.setListValueId(attribute.getId(), Integer.parseInt(param.getValue()));
                    break;
                case REFERENCE:
                    order.setReferenceId(attribute.getId(), Integer.parseInt(param.getValue()));
                    break;
                default:
                    order.setStringValue(attribute.getId(), param.getValue());
                    break;
            }
        }
    }
}
