package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class NonIncludeException extends RuntimeException {
    public NonIncludeException(ErrorMessage message) {
        super(message.message());
    }
}
