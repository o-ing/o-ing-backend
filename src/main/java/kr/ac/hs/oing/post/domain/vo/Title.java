package kr.ac.hs.oing.post.domain.vo;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Title {
    @Transient
    private static final String TITLE_VALIDATOR = "^.{1,50}$";

    @Column(name = "post_title", nullable = false)
    private String title;

    protected Title() {
    }

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    public void validate(String title) {
        if (!Pattern.matches(TITLE_VALIDATOR, title)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_BOARD_NAME);
        }
    }

    public String getTitle() {
        return title;
    }
}
