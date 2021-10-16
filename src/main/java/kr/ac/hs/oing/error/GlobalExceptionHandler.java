package kr.ac.hs.oing.error;

import kr.ac.hs.oing.error.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
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

    @ExceptionHandler(InvalidJwtException.class)
    protected ResponseEntity<ErrorResponseDto> handleInvalidJwtException(InvalidJwtException exception) {
        ErrorMessage errorMessage = ErrorMessage.of(exception.getMessage());
        ErrorResponseDto response = ErrorResponseDto.of(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonExitsException.class)
    protected ResponseEntity<ErrorResponseDto> handleNonExitsException(NonExitsException exception) {
        ErrorMessage errorMessage = ErrorMessage.of(exception.getMessage());
        ErrorResponseDto response = ErrorResponseDto.of(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    protected ResponseEntity<ErrorResponseDto> handleAuthException(AuthException exception) {
        ErrorMessage errorMessage = ErrorMessage.of(exception.getMessage());
        ErrorResponseDto response = ErrorResponseDto.of(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonIncludeException.class)
    protected ResponseEntity<ErrorResponseDto> handleNonIncludeException(NonIncludeException exception) {
        ErrorMessage errorMessage = ErrorMessage.of(exception.getMessage());
        ErrorResponseDto response = ErrorResponseDto.of(errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
