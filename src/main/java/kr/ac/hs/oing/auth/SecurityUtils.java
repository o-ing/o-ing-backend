package kr.ac.hs.oing.auth;

import kr.ac.hs.oing.exception.ErrorMessage;
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
            throw new RuntimeException(ErrorMessage.NOT_EXIST_MEMBER.message());
        }
    }
}
