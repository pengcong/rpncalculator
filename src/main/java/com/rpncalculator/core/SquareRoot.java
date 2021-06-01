package com.rpncalculator.core;

import java.math.BigDecimal;

public class SquareRoot implements OperatorCalculator {

    @Override
    public void calculate(OperandStackInMemory<BigDecimal> operandStack) {
        BigDecimal op = operandStack.pop();
        operandStack.push(BigDecimal.valueOf(Math.sqrt(op.doubleValue())));
    }
}
