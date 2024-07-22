package com.onsafety.backend_prova_pratica.Exception;


public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
