package com.rpncalculator.token;

import java.util.Set;
import java.util.regex.Pattern;

public class Tokenizer {

    private static final Pattern spaceRegex = Pattern.compile("[ \\t\\n]");
    private static final Pattern numberRegex = Pattern.compile("^[0-9]+(.[0-9]+)?$");
    private Set<String> operators;
    private String line;
    private int position;
    private boolean inSpace = true;
    private int startPosition = 0;

    public Tokenizer(String line, Set<String> operators) {
        this.line = line;
        this.operators = operators;
    }

    public boolean hasNext() {
        if (inSpace) {
            while (position < line.length()) {
                if (!isSpace(line.charAt(position))) {
                    inSpace = false;
                    startPosition = position;
                    return true;
                }
                position++;
            }
            return false;
        } else {
            return true;
        }
    }

    public Token nextToken() {
        int endPosition;
        if (hasNext()) {
            while (position < line.length() && !isSpace(line.charAt(position))) {
                position++;
            }
            inSpace = true;
            endPosition = position;
            return createToken(line.substring(startPosition, endPosition), startPosition + 1);
        }
        return null;
    }

    private Token createToken(String token, int position) {
        if (isNumber(token)) {
            return new NumberToken(token, position);
        } else if (isOperator(token)) {
            return new OperatorToken(token, position);
        }
        throw new InvalidTokenException(token, position);
    }

    private boolean isOperator(String substring) {
        return operators.contains(substring);
    }

    private static boolean isNumber(String token) {
        return numberRegex.matcher(token).matches();
    }

    private static boolean isSpace(char c) {
        return spaceRegex.matcher(String.valueOf(c)).matches();
    }
}
