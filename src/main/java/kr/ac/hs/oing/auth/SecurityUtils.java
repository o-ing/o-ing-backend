package kr.ac.hs.oing.auth;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<String> getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        isNullAuthentication(authentication);

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return Optional.ofNullable(springSecurityUser.getUsername());
        }
        return Optional.ofNullable((String) authentication.getPrincipal());
    }

    private static void isNullAuthentication(Authentication authentication) {
        if (authentication == null) {
            throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
        }
    }
}