package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.member.exception.InvalidArgumentException;
import kr.ac.hs.oing.member.exception.MemberExceptionMessage;
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
            throw new InvalidArgumentException(MemberExceptionMessage.NICKNAME);
        }
    }

    @Override
    public String toString() {
        if (this.nickname() == null) {
            throw new InvalidArgumentException(MemberExceptionMessage.NICKNAME);
        }
        return this.nickname();
    }

    public String nickname() {
        return nickname;
    }
}
