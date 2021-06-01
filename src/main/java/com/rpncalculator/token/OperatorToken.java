package com.rpncalculator.token;

public class OperatorToken implements Token {

    private String value;
    private int position;

    public OperatorToken(String value, int position) {
        this.value = value;
        this.position = position;
    }

    @Override
    public TokenType getType() {
        return TokenType.OPERATOR;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}
