package kr.ac.hs.oing.exception;

public class ActivateArgumentException extends RuntimeException {
    public ActivateArgumentException(ErrorMessage message) {
        super(message.message());
    }
}