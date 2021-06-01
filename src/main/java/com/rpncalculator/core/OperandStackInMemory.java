package com.rpncalculator.core;

import java.io.PrintWriter;
import java.util.Stack;

public class OperandStackInMemory<T> implements OperandStack<T> {

    private Stack<T> data;
    private Stack<Stack<Action>> undoLogs;
    private TransactionImpl currentTransaction;

    public OperandStackInMemory() {
        this.data = new Stack<>();
        this.undoLogs = new Stack<>();
    }

    public void push(T element) {
        Stack<Action> undo = getUndo();
        data.push(element);
        undo.push(() -> data.pop());
    }

    public T pop() {
        T value = data.pop();
        Stack<Action> undo = getUndo();
        undo.push(() -> data.push(value));
        return value;
    }

    public void clear() {
        Stack<Action> undo = getUndo();
        Stack<T> savedData = data;
        undo.push(() -> data = savedData);
        data = new Stack<>();
    }

    private Stack<Action> getUndo() {
        Stack<Action> undo;
        if (currentTransaction != null) {
            undo = currentTransaction.undoStack;
        } else {
            undo = new Stack<>();
            undoLogs.push(undo);
        }
        return undo;
    }

    @Override
    public Transaction startTransaction() {
        currentTransaction = new TransactionImpl(undoLogs);
        return currentTransaction;
    }

    @Override
    public void undo() {
        Stack<Action> undo = undoLogs.pop();
        while (!undo.isEmpty()) {
            undo.pop().execute();
        }
    }

    @Override
    public void printStack(PrintWriter pw) {
        for (T element : data) {
            try {
                pw.write(element.toString() + " ");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class TransactionImpl implements Transaction {

        private Stack<Stack<Action>> undoLogs;
        private Stack<Action> undoStack = new Stack<>();

        public TransactionImpl(Stack<Stack<Action>> undoLogs) {
            this.undoLogs = undoLogs;
        }

        @Override
        public void commit() {
            if (!undoStack.isEmpty()) {
                undoLogs.push(undoStack);
            }
            currentTransaction = null;
        }

        @Override
        public void rollback() {
            while (!undoStack.isEmpty()) {
                undoStack.pop().execute();
            }
            currentTransaction = null;
        }
    }
}
