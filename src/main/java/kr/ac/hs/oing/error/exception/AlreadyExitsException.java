package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class AlreadyExitsException extends BusinessException {
    public AlreadyExitsException(ErrorMessage message) {
        super(message);
    }
}
