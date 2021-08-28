package kr.ac.hs.oing.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthException extends UsernameNotFoundException {
    public AuthException(ErrorMessage errorMessage) {
        super(errorMessage.message());
    }
}
