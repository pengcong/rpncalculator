package com.rpncalculator.core;

import java.math.BigDecimal;

public class Mutiplier implements OperatorCalculator {

    @Override
    public void calculate(OperandStackInMemory<BigDecimal> operandStack) {
        BigDecimal op1 = operandStack.pop();
        BigDecimal op2 = operandStack.pop();
        operandStack.push(op2.multiply(op1));
    }
}
