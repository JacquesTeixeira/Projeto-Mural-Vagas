package br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions;

import java.io.Serializable;

public class DataIntegrityException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}