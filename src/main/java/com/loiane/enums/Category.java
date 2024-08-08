package com.loiane.enums;

public enum Category {
    BACK_END("back-end"), FRONT_END("front-end");

    private String value;
    
    private Category(String value) {
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

