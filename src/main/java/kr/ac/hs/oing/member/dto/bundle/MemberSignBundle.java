package kr.ac.hs.oing.member.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignBundle {

    private final Email email;
    private final Password password;
    private final Name name;
    private final Nickname nickname;
    private final PhoneNumber phoneNumber;

    @Builder
    public MemberSignBundle(String email, String password, String name,
        String nickname, String phoneNumber) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.name = new Name(name);
        this.nickname = new Nickname(nickname);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }
}
