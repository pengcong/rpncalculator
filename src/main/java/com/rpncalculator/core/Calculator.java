package com.rpncalculator.core;

import com.rpncalculator.token.Token;
import com.rpncalculator.token.TokenType;
import com.rpncalculator.token.Tokenizer;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private OperandStackInMemory<BigDecimal> operands = new OperandStackInMemory<>();

    private Map<String, OperatorCalculator> calculators = new HashMap<>();

    public Calculator() {
        registerOperator("+", new Adder());
        registerOperator("-", new Subtracter());
        registerOperator("*", new Mutiplier());
        registerOperator("/", new Divider());
        registerOperator("sqrt", new SquareRoot());
        registerOperator("clear", new Clearer());
        registerOperator("undo", new Undoer());
    }

    public void calculate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression, calculators.keySet());
        while (tokenizer.hasNext()) {
            Token token = tokenizer.nextToken();
            if (token.getType() == TokenType.NUMBER) {
                operands.push(new BigDecimal(token.getValue()));
            } else if (token.getType() == TokenType.OPERATOR) {
                OperatorCalculator calc = calculators.get(token.getValue());
                Transaction trans = operands.startTransaction();
                try {
                    calc.calculate(operands);
                    trans.commit();
                } catch (EmptyStackException ese) {
                    trans.rollback();
                    throw new InsufficientParametersException(token.getValue(), token.getPosition());
                } catch (Exception ex) {
                    trans.rollback();
                    throw ex;
                }
            }
        }
    }

    public void registerOperator(String operator, OperatorCalculator calculator) {
        calculators.put(operator, calculator);
    }

    public void printStack(PrintWriter pw) {
        operands.printStack(pw);
    }
}
