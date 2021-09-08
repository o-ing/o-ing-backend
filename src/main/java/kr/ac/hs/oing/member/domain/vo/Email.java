package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.exception.InvalidArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Transient
    private static final String EMAIL_VALIDATOR = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";

    @Column(name = "member_email", nullable = false)
    private String email;


    public Email(String email) {
        validate(email);
        this.email = email;
    }

    public void validate(String email) {
        if (!StringUtils.hasText(email) || !Pattern.matches(EMAIL_VALIDATOR, email)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_EMAIL);
        }
    }

    @Override
    public String toString() {
        return email;
    }

}
