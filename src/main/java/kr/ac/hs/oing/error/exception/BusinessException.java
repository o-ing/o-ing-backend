package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class BusinessException extends RuntimeException {
    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.message());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
