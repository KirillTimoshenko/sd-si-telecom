package com.netcracker.ec.exceptions;

public class OmResolverException extends RuntimeException {
    public OmResolverException(String errorMessage) {
        super(errorMessage);
    }

    public OmResolverException(String message, Throwable cause) {
        super(message, cause);
    }
}
