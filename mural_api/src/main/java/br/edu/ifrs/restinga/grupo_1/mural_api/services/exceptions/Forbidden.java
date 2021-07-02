package br.edu.ifrs.restinga.grupo_1.mural_api.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Forbidden extends RuntimeException {
    public Forbidden(String message) {
        super(message);
    }
        public Forbidden(String message, Throwable cause) {
            super(message,cause);
        }
}