package com.rpncalculator.core;

public class InsufficientParametersException extends RuntimeException {

    private String operator;
    private int position;

    public InsufficientParametersException(String value, int position) {
        this.operator = value;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "operator " + operator + " (position: " + position + "): insufficient parameters";
    }

    public String getOperator() {
        return operator;
    }

    public int getPosition() {
        return position;
    }
}
