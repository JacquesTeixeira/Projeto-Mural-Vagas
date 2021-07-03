package br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataIntegrityException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}