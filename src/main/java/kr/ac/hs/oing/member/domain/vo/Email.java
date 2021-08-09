package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.member.exception.InvalidArgumentException;
import kr.ac.hs.oing.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Transient
    private static final String EMAIL_VALIDATOR = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@" + "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";

    @Transient
    private static final String PART_VALIDATOR = "@";

    @Transient
    private static final String EMAIL_FORMAT = "%s@%s";

    @Transient
    private static final int LOCAL_PART_NUMBER = 0;

    @Transient
    private static final int DOMAIN_PART_NUMBER = 1;

    @Column(name = "member_email_local_part", nullable = false)
    private String emailLocalPart;

    @Column(name = "member_email_domian_part", nullable = false)
    private String emailDomainPart;

    public Email(String email) {
        System.out.println("---------------");
        System.out.println("> " + email + "<");
        System.out.println("---------------");
        validate(email);
        divideMail(email);
    }

    private void divideMail(String email) {
        List<String> dividedEmail = splitEntireEmail(email);
        this.emailLocalPart = dividedEmail.get(LOCAL_PART_NUMBER);
        this.emailDomainPart = dividedEmail.get(DOMAIN_PART_NUMBER);
    }

    private List<String> splitEntireEmail(String email) {
        return Arrays.asList(email.split(PART_VALIDATOR));
    }


    public void validate(String email) {
        if (!StringUtils.hasText(email) || !Pattern.matches(EMAIL_VALIDATOR, email)) {
            throw new InvalidArgumentException(MemberExceptionMessage.EMAIL);
        }
    }

    @Override
    public String toString() {
        if (this.EmailLocalPart() == null || this.EmailDomainPart() == null) {
            throw new InvalidArgumentException(MemberExceptionMessage.EMAIL);
        }
        return String.format(EMAIL_FORMAT, this.EmailDomainPart(), this.EmailLocalPart());
    }

    public String EmailLocalPart() {
        return emailLocalPart;
    }

    public String EmailDomainPart() {
        return emailDomainPart;
    }
}
