package com.vf.exception;
public class MasterOrderNotFoundException extends Exception {
private String id;
public MasterOrderNotFoundException(Long id) {
        super(String.format("Master order not found with id : '%s'", id));
        }
}