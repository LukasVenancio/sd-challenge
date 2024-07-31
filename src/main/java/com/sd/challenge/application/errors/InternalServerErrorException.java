package com.sd.challenge.application.errors;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super("There was an error processing the request.");
    }

}
