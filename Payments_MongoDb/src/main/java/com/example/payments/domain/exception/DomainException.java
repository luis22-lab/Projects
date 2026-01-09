package com.example.payments.domain.exception;

public class DomainException extends RuntimeException {

    private final int code;

    public DomainException(int code) {
        this.code = code;
    }

    public DomainException(int code, String message) {
        super(message);
        this.code = code;
    }
    public DomainException(int code, String message, Throwable cause) {

        super(message, cause);
        this.code = code;
    }
    public DomainException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

}
