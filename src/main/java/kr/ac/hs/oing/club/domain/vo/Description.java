package kr.ac.hs.oing.club.domain.vo;

import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.exception.InvalidArgumentException;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
public class Description {
    @Column(name = "club_description")
    private String description;

    protected Description() {

    }

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
