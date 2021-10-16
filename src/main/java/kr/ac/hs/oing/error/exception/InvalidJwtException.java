package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(ErrorMessage message) {
        super(message.name());
    }
}
