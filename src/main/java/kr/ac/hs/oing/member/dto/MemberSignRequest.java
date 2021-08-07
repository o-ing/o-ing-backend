package kr.ac.hs.oing.member.dto;


import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberSignRequest {
    private final Email email;
    private final Password password;
    private final Nickname nickname;
    private final PhoneNumber phoneNumber;

    public Member sign() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();
    }

    public Email email() {
        return email;
    }

    public Password password() {
        return password;
    }

    public Nickname nickname() {
        return nickname;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }
}
