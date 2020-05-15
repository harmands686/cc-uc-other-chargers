package com.vf.exception;
public class ChildOrderNotFoundException extends Exception {
private String id;
public ChildOrderNotFoundException(Long id) {
        super(String.format("Child order not found with id : '%s'", id));
        }
}