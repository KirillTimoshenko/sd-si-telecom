package com.netcracker.ec.services.console;

import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.domain.enums.ConsoleOperation;
import com.netcracker.ec.model.domain.enums.OrderAim;
import com.netcracker.ec.provisioning.operations.*;
import com.netcracker.ec.services.util.AttributeValueManager;
import lombok.Getter;

import java.util.*;

import static com.netcracker.ec.common.TelecomConstants.POSITIVE_ANSWER;

public class Console {
    @Getter
    private final Scanner scanner;
    private static Console console = null;

    private Console() {
        this.scanner = new Scanner(System.in);
    }

    public static Console getInstance() {
        if (console == null) {
            console = new Console();
        }
        return console;
    }

    public Operation getNextOperation() {
        Operation operation = null;
        printConsoleOperations();
        String operationId = console.nextConsoleOperationCommand();

        switch (ConsoleOperation.getOperationById(operationId)) {
            case DISCONNECT_ORDER:
                operation = new DisconnectOrderOperation();
                break;
            case CREATE_ORDER:
                operation = new CreateOrderOperation();
                break;
            case SHOW_ORDERS:
                operation = new ShowOrdersOperation();
                break;
            case EXIT:
                operation = new ExitOperation();
                break;
            default:
                break;
        }
        return operation;
    }

    public void printConsoleOperations() {
        System.out.println("  Operations:");
        Arrays.stream(ConsoleOperation.values()).forEach(System.out::println);
    }

    public void printAvailableOperations(Map<Integer, String> operationsMap) {
        operationsMap.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public void printEntityList(List<? extends NcEntity> entityList) {
        entityList.forEach(entity -> System.out.println(entity.toFormattedOutput()));
    }

    public void printOrderInfo(NcObject object) {
        AttributeValueManager attributeValueManager = new AttributeValueManager(scanner);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order name: ")
                .append(object.getName())
                .append("\n");
        object.getParams().forEach((attr, value) ->
                stringBuilder.append("  ")
                        .append(attr.getName())
                        .append(": ")
                        .append(attributeValueManager.getAttributeValueFromOrder(object, attr))
                        .append("\n"));
        System.out.println(stringBuilder.toString());
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }

    public String nextOperationCommand() {
        return scanner.next();
    }

    public Integer nextOperationId() {
        return scanner.nextInt();
    }

    public Integer nextAvailableOperation(Set<Integer> availableOperationsSet) {
        Integer id;
        do {
            id = console.nextOperationId();
        } while (!availableOperationsSet.contains(id));
        return id;
    }

    public String nextConsoleOperationCommand() {
        String command;
        do {
            command = console.nextOperationCommand();
        } while (!ConsoleOperation.contains(command));
        return command;
    }

    public boolean getSaveDialogueAnswer() {
        System.out.println("Save order?[Y/N]");
        return POSITIVE_ANSWER.equalsIgnoreCase(scanner.next());
    }

    public void close() {
        System.out.println("Exit in process...");
        scanner.close();
    }
}