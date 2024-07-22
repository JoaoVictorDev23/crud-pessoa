package com.onsafety.backend_prova_pratica.Exception;
/**
 * Exceção personalizada para CPF inválido.
 */
public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String message) {
        super(message);
    }
}