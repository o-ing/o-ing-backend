package kr.ac.hs.oing.member.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Password;
import kr.ac.hs.oing.member.domain.vo.PhoneNumber;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateBundle {

    private final Email email;
    private final Password password;
    private final Nickname nickname;
    private final PhoneNumber phoneNumber;

    @Builder
    public MemberUpdateBundle(String email, String password, String nickname, String phoneNumber) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.nickname = new Nickname(nickname);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }
}