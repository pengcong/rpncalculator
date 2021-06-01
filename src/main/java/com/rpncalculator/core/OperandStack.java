package com.rpncalculator.core;

import java.io.PrintWriter;

public interface OperandStack<T> {

    void push(T element);

    T pop();

    void clear();

    Transaction startTransaction();

    void undo();

    void printStack(PrintWriter printWriter);
}
