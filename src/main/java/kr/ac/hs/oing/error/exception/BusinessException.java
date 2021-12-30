package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage message) {
        super(message.message());
        this.errorMessage = message;
    }
}
