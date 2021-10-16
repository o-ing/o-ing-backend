package kr.ac.hs.oing.error.exception;

import kr.ac.hs.oing.error.ErrorMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthException extends UsernameNotFoundException {
    public AuthException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
