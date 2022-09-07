package com.bitcamp.travelkkaebi.exception;

public class KkaebiException extends RuntimeException {
    ErrorCode errorCode;
    String message;
    public KkaebiException(ErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
        this.message = errorCode.message;
    }

    public KkaebiException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}