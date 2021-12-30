package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import kr.ac.hs.oing.error.ErrorMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Transient
    private static final String PASSWORD_VALIDATOR = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[가-힣A-Za-z\\d$@$!%*#?&]{8,30}$";

    @Column(name = "member_password", nullable = false)
    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    private Password(PasswordEncoder passwordEncoder, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        this.password = encodedPassword;
    }

    public Password encode(PasswordEncoder passwordEncoder) {
        return new Password(passwordEncoder, password);
    }

    public void validate(String password) {
        if (!Pattern.matches(PASSWORD_VALIDATOR, password)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_MEMBER_PASSWORD);
        }
    }
}
