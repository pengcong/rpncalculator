package com.rpncalculator.token;

public class InvalidTokenException extends RuntimeException {

    private String token;
    private int position;

    public InvalidTokenException(String substring, int position) {
        this.token = substring;
        this.position = position;
    }

    public String getToken() {
        return token;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String getMessage() {
        return "invalid token " + token + " (position: " + position + ")";
    }
}
