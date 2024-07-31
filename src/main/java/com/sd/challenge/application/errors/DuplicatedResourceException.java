package com.sd.challenge.application.errors;

public class DuplicatedResourceException extends RuntimeException {

    public DuplicatedResourceException(Class entityClass) {
        super("Duplicated entity: " + entityClass.getSimpleName());
    }
}
