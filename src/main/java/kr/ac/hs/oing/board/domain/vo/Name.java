package kr.ac.hs.oing.board.domain.vo;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Name {
    @Transient
    private static final String NAME_VALIDATOR = "^.{1,50}$";

    @Column(name = "board_name", nullable = false)
    private String name;

    protected Name() {
    }

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (!Pattern.matches(NAME_VALIDATOR, name)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_BOARD_NAME);
        }
    }

    public String getName() {
        return name;
    }
}
