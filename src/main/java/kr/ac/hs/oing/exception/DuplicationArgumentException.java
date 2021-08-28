package kr.ac.hs.oing.exception;

public class DuplicationArgumentException extends IllegalArgumentException {
    public DuplicationArgumentException(ErrorMessage message) {
        super(message.message());
    }
}
