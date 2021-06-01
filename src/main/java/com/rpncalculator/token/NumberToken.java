package com.rpncalculator.token;

public class NumberToken implements Token {

    private String value;
    private int position;

    public NumberToken(String value, int position) {
        this.value = value;
        this.position = position;
    }

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
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
