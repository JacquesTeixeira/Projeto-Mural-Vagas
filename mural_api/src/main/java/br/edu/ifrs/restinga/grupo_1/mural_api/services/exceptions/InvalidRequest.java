package br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequest extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public InvalidRequest(String message) {
        super(message);
    }

    public InvalidRequest(String message, Throwable cause) {
        super(message,cause);
    }
}
