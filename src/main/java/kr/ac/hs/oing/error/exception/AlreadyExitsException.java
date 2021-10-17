package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class AlreadyExitsException extends RuntimeException {
    public AlreadyExitsException(ErrorMessage message) {
        super(message.message());
    }
}
