package kr.ac.hs.oing.member.dto;


import kr.ac.hs.oing.member.domain.Authority;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@RequiredArgsConstructor
public class MemberSignDto {
    private final Email email;
    private final Password password;
    private final Name name;
    private final Nickname nickname;
    private final PhoneNumber phoneNumber;

    public Member sign(PasswordEncoder passwordEncoder) {
        Password encodedPassword = password.encode(passwordEncoder);

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .authorities(Collections.singleton(authority))
                .activated(true)
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
