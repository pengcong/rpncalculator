package com.rpncalculator.core;

import java.math.BigDecimal;

public interface OperatorCalculator {

    void calculate(OperandStackInMemory<BigDecimal> operandStack);
}
