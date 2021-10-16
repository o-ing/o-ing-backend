package kr.ac.hs.oing.member.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.*;
import lombok.Builder;

@Builder
public class MemberSignBundle {
    private final Email email;
    private final Password password;
    private final Name name;
    private final Nickname nickname;
    private final PhoneNumber phoneNumber;

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Name getName() {
        return name;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
