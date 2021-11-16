package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;

public class NoPermissionException extends BusinessException {
    public NoPermissionException(ErrorMessage message) {
        super(message);
    }
}
