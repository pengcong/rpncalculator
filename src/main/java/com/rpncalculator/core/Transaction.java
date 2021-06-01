package com.rpncalculator.core;

public interface Transaction {

    void commit();

    void rollback();
}
