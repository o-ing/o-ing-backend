package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.exception.InvalidArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
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
    private static final String NAME_VALIDATOR = "^[가-힣]{2,10}$";

    @Column(name = "member_name", nullable = false)
    private String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (!Pattern.matches(NAME_VALIDATOR, name)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_NAME);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
