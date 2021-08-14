package kr.ac.hs.oing.member.dto;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Password;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberLoginRequest {
    private final Email email;
    private final Password password;

    public Email email() {
        return email;
    }

    public Password password() {
        return password;
    }

}
