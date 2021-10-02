package kr.ac.hs.oing.club.domain.vo;

import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Description {
    @Transient
    private static final String DESCRIPTION_VALIDATOR = "^[a-zA-Z가-힣0-9]{1,200}$";

    @Column(name = "club_description")
    private String description;

    protected Description() {

    }

    public Description(String description) {
        validate(description);
        this.description = description;
    }

    public void validate(String description) {
        if (!Pattern.matches(DESCRIPTION_VALIDATOR, description)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_CLUB_DESCRIPTION);
        }
    }

    public String getDescription() {
        return description;
    }
}
