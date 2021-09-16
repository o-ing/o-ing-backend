package kr.ac.hs.oing.exception;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(ErrorMessage message) {
        super(message.name());
    }
}
