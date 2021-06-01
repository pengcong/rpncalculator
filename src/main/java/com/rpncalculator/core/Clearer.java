package com.rpncalculator.core;

import java.math.BigDecimal;

public class Clearer implements OperatorCalculator {

    @Override
    public void calculate(OperandStackInMemory<BigDecimal> operandStack) {
        operandStack.clear();
    }
}
