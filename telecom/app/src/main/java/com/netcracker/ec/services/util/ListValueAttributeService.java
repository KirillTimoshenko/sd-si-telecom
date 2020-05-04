package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcListValueService;
import com.netcracker.ec.services.db.impl.NcListValueServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListValueAttributeService {
    private final NcListValueService ncListValueService;

    private final Console console = Console.getInstance();

    public ListValueAttributeService() {
        this.ncListValueService = new NcListValueServiceImpl();
    }

    public String read(NcAttribute attr) {
        console.printMessage("Please, choose list value.");

        Map<Integer, String> listValuesMap = getListValuesMap(attr);
        console.printAvailableOperations(listValuesMap);

        Integer listValueId = console.nextAvailableOperation(listValuesMap.keySet());

        return listValueId.toString();
    }

    private Map<Integer, String> getListValuesMap(NcAttribute attr) {
        List<NcEntity> list = ncListValueService.getNcListValuesAsEntitiesByNcAttrTypeDefId(
                attr.getAttrTypeDef().getId());

        Map<Integer, String> listValuesMap = new HashMap<>();
        list.forEach(lv -> listValuesMap.put(lv.getId(), lv.getName()));
        return listValuesMap;
    }
}
