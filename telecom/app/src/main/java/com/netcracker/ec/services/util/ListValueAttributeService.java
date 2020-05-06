package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.NcListValueService;
import com.netcracker.ec.services.db.impl.NcListValueServiceImpl;

import java.util.List;

public class ListValueAttributeService {
    private final NcListValueService ncListValueService;

    private final Console console = Console.getInstance();

    public ListValueAttributeService() {
        this.ncListValueService = new NcListValueServiceImpl();
    }

    public String read(NcAttribute attr) {
        console.printMessage("Please, choose list value.");

        List<NcEntity> listValues = ncListValueService.getNcListValuesAsEntitiesByNcAttrTypeDefId(
                attr.getAttrTypeDef().getId());
        console.printEntityList(listValues);

        Integer listValueId = console.nextAvailableOperation(EntityIdManager.getIdSet(listValues));

        return listValueId.toString();
    }
}
