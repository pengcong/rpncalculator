package com.rpncalculator.core;

import java.math.BigDecimal;

public class Undoer implements OperatorCalculator {

    @Override
    public void calculate(OperandStackInMemory<BigDecimal> operandStack) {
        operandStack.undo();
        operandStack.undo();
    }
}
