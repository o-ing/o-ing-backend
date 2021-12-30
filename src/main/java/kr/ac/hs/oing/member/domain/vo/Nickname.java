package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.error.exception.InvalidArgumentException;
import kr.ac.hs.oing.error.ErrorMessage;
import lombok.AccessLevel;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nickname {

    @Transient
    private static final String NICKNAME_VALIDATOR = "^[a-zA-Z가-힣0-9]{2,20}$";

    @Column(name = "member_nickname", nullable = false)
    private String nickname;

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }
    
    public void validate(String nickname) {
        if (!Pattern.matches(NICKNAME_VALIDATOR, nickname)) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_MEMBER_NICKNAME);
        }
    }
}
