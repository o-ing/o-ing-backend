package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class NonExitsException extends BusinessException {
    public NonExitsException(ErrorMessage message) {
        super(message);
    }
}
