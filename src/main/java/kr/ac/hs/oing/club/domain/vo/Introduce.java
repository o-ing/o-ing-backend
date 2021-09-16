package kr.ac.hs.oing.club.domain.vo;

import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.exception.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Introduce {

    @Transient
    private static final String INTRODUCE_VALIDATOR = "^[a-zA-Z가-힣0-9]{1,200}$";

    @Column(name = "club_introduce", nullable = false)
    private String introduce;

    public Introduce(String introduce) {
        validate(introduce);
        this.introduce = introduce;
    }

    public void validate(String introduce) {
        if (!Pattern.matches(INTRODUCE_VALIDATOR, introduce)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_CLUB_INTRODUCE);
        }
    }

    public String getIntroduce() {
        return introduce;
    }
}
