package kr.ac.hs.oing.exception;

public class NonExitsException extends RuntimeException {
    public NonExitsException(ErrorMessage message) {
        super(message.message());
    }
}
