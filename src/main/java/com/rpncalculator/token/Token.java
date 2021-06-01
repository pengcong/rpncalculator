package com.rpncalculator.token;

public interface Token {

    TokenType getType();

    int getPosition();

    String getValue();
}
