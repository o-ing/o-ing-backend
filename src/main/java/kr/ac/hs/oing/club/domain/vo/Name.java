package kr.ac.hs.oing.club.domain.vo;

import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@RequiredArgsConstructor
public class Name {

    @Transient
    private static final String NAME_VALIDATOR = "^[a-zA-Z가-힣0-9]{1,20}$";

    @Column(name = "club_name", nullable = false)
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (!Pattern.matches(NAME_VALIDATOR, name)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_CLUB_NAME);
        }
    }

    public String getName() {
        return name;
    }

}