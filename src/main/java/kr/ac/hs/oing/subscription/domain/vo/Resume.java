package kr.ac.hs.oing.subscription.domain.vo;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
    @Transient
    private static final String RESUME_VALIDATOR = "^.{1,2000}$";

    @Getter
    @Column(name = "subscription_resume")
    private String resume;

    public Resume(String resume) {
        validate(resume);
        this.resume = resume;
    }

    public void validate(String resume) {
        if (!Pattern.matches(RESUME_VALIDATOR, resume)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_SUBSCRIPTION_RESUME);
        }
    }
}

