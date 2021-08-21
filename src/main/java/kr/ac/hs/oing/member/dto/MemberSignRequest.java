package kr.ac.hs.oing.member.dto;


import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MemberSignRequest {
    private final Email email;
    private final Password password;
    private final Name name;
    private final Nickname nickname;
    private final PhoneNumber phoneNumber;

    public Member sign(PasswordEncoder passwordEncoder) {
        Password encodedPassword = password.encode(passwordEncoder);

        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();
    }

    public Email email() {
        return email;
    }

    public Nickname nickname() {
        return nickname;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }
}
