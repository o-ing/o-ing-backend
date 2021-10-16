package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class InvalidArgumentException extends IllegalArgumentException {
    public InvalidArgumentException(ErrorMessage message) {
        super(message.message());
    }
}
