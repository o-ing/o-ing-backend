package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import kr.ac.hs.oing.error.ErrorMessage;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
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
            throw new InvalidArgumentException(ErrorMessage.INVALID_MEMBER_NAME);
        }
    }
}
