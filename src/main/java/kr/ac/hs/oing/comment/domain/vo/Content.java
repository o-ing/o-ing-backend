package kr.ac.hs.oing.comment.domain.vo;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Content {
    @Transient
    private static final String CONTENT_VALIDATOR = "^.{1,2000}$";

    @Column(name = "comment_content", nullable = false)
    private String content;

    protected Content() {
    }

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    public void validate(String content) {
        if (!Pattern.matches(CONTENT_VALIDATOR, content)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_COMMENT_CONTENT);
        }
    }

    public String getContent() {
        return content;
    }
}
