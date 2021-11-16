package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class DuplicationArgumentException extends WrongInputException {
    public DuplicationArgumentException(ErrorMessage message) {
        super(message);
    }
}
