package ru.gb.shop.api;

import java.util.List;
import java.util.stream.Collectors;


public class ValidationException extends RuntimeException {
    private List<String> errorFieldsMessage;
    public ValidationException(List<String> errorFieldsMessage) {
        super(errorFieldsMessage.stream().collect(Collectors.joining(",")));
        this.errorFieldsMessage = errorFieldsMessage;
    }

    public List<String> getErrorFieldsMessage() {
        return errorFieldsMessage;
    }

    public void setErrorFieldsMessage(List<String> errorFieldsMessage) {
        this.errorFieldsMessage = errorFieldsMessage;
    }
}
