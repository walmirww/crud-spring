package com.loiane.enums;

public enum Status {
    ACTIVE("Active"), INACTIVE("Inactive");

    private String value;
    
    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //toString
    @Override
    public String toString() {
        return value;
    }
}
