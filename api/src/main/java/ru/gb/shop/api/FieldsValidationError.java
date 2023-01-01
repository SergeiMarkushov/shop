package ru.gb.shop.api;


import java.util.List;

public class FieldsValidationError {
    private List<String> errorFieldMessages;

    public FieldsValidationError(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }


    public List<String> getErrorFieldMessages() {
        return errorFieldMessages;
    }

    public void setErrorFieldMessages(List<String> errorFieldMessages) {
        this.errorFieldMessages = errorFieldMessages;
    }
}
