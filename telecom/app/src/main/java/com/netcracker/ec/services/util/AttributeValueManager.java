package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.domain.order.Order;

import java.util.Scanner;
import java.util.Set;

public class AttributeValueManager {

    private final Scanner scanner;

    public AttributeValueManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void readOrderAttributes(Order order, Set<NcAttribute> attributes) {
        attributes.forEach(attr -> order
                .setParam(attr, readAttribute(attr)));
    }

    public void mergerOrderAttributes(Order order) {
        order.getParams().forEach((key, value) -> mergerAttribute(order, key, value));
    }

    private String readAttribute(NcAttribute attr) {
        switch (attr.getAttrTypeDef().getType()) {
            case TEXT:
                return readText(attr);
            case NUMBER:
                return readNumber(attr);
            case DECIMAL:
                return readDecimal(attr);
            case DATE:
                return readDate(attr);
            case REFERENCE:
                return readReference(attr);
            case LIST:
                return readList(attr);
            default:
                break;
        }
        return null;
    }

    private void mergerAttribute(Order order, NcAttribute attr, String value) {
        switch (attr.getAttrTypeDef().getType()) {
            case REFERENCE:
                order.setReferenceId(attr.getId(), Integer.parseInt(value));
            case LIST:
                order.setListValueId(attr.getId(), Integer.parseInt(value));
            default:
                order.setStringValue(attr.getId(), value);
        }
    }

    private String readText(NcAttribute attr) {
        System.out.print(attr.getName() + ": ");
        return scanner.next();
    }

    private String readNumber(NcAttribute attr) {
        System.out.print(attr.getName() + ": ");
        return String.valueOf(scanner.nextInt());
    }

    private String readDecimal(NcAttribute attr) {
        System.out.print(attr.getName() + ": ");
        return String.valueOf(scanner.nextDouble());
    }

    private String readDate(NcAttribute attr) {
        System.out.print(attr.getName() + ": ");
        return scanner.next();
    }

    private String readReference(NcAttribute attr) {
        return new ReferenceAttributeService().read(attr);
    }

    private String readList(NcAttribute attr) {
        return new ListValueAttributeService().read(attr);
    }
}