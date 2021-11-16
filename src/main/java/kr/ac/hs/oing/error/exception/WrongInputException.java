package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class WrongInputException extends IllegalArgumentException {
    private ErrorMessage errorMessage;

    public WrongInputException(ErrorMessage message) {
        super(message.message());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}