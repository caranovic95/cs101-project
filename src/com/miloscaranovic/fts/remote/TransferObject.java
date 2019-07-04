/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.remote;

import java.io.Serializable;

public class TransferObject implements Serializable {
    // Sluzi za serijalizaciju kroz bilo sta, sluzi za komunikaciju
    
    // komunikacija klijenta i servera
    public static final int GET_ALL_INSTRUCTORS = 1;
    public static final int GET_ALL_TRAINEES = 2;
    public static final int GET_ALL_FITNESS_TESTS = 3;
    public static final int INSERT_FITNESS_TEST = 4;
    public static final int UPDATE_FITNESS_TEST = 5;
    public static final int DELETE_FITNESS_TEST = 6;
    public static final int ADMIN_LOGIN = 7;

    private int operation;
    private Object parameter; // moze da bude prazan, moze da bude admin
    private Object result;
    private Object exception;

    public TransferObject() {
    }

    public TransferObject(int operation, Object parameter) {
        this.operation = operation;
        this.parameter = parameter;
    }

    public TransferObject(int operation, Object result, Object exception) {
        this.operation = operation;
        this.result = result;
        this.exception = exception;
    }

    public TransferObject(int operation, Object parameter, Object result, Object exception) {
        this.operation = operation;
        this.parameter = parameter;
        this.result = result;
        this.exception = exception;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }
}
