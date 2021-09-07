package kr.ac.hs.oing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // exception에서 꺼내와서 status를 넘겨주자!
    @ExceptionHandler(InvalidArgumentException.class)
    protected ResponseEntity<ErrorResponseDto> handleInvalidArgumentException(InvalidArgumentException exception) {
        ErrorMessage errorMessage = ErrorMessage.of(exception.getMessage());
        ErrorResponseDto response = ErrorResponseDto.of(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicationArgumentException.class)
    protected ResponseEntity<ErrorResponseDto> handleDuplicationArgumentException(DuplicationArgumentException exception) {
        ErrorMessage errorMessage = ErrorMessage.of(exception.getMessage());
        ErrorResponseDto response = ErrorResponseDto.of(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponseDto> handleAuthException() {
        ErrorResponseDto response = ErrorResponseDto.of(ErrorMessage.LOGIN_FAIL);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
