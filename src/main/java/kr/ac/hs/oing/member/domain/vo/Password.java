package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.member.exception.InvalidArgumentException;
import kr.ac.hs.oing.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    /**
     * 최소 8자 ~ 최대 30자
     * 최소 영문자 1자
     * 최소 숫자 1자
     * 최소 특수문자 1자 :: $@$!%*#?&
     * ^(?=.*[~!@#$%^&*()_+`\-=\[\]\{\};':\",.\<>?])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\S{8,30}$
     */
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
        if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD_VALIDATOR, password)) {
            throw new InvalidArgumentException(MemberExceptionMessage.PASSWORD);
        }
    }


    @Override
    public String toString() {
        return this.password();
    }

    public String password() {
        return password;
    }

}
