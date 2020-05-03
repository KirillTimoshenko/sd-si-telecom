package com.netcracker.ec.model.db;

import com.netcracker.ec.model.domain.enums.AttributeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NcAttrTypeDef {
    private Integer id;
    private AttributeType type;
    private NcObjectType objectType;
}
