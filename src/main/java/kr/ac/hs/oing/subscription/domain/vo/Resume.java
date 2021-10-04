package kr.ac.hs.oing.subscription.domain.vo;

import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Resume {
    @Transient
    private static final String RESUME_VALIDATOR = "^.{1,2000}$";

    @Column(name = "subscription_resume")
    private String resume;

    protected Resume() {

    }

    public Resume(String resume) {
        validate(resume);
        this.resume = resume;
    }

    public void validate(String resume) {
        if (!Pattern.matches(RESUME_VALIDATOR, resume)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_SUBSCRIPTION_RESUME);
        }
    }

    public String getResume() {
        return resume;
    }
}

