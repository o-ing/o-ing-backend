package kr.ac.hs.oing.auth;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Password;
import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
public class LoginMember {
    private final int id;
    private final Email email;
    private final Password password;
    private final Role role;

    public Email loginEmail() {
        return email;
    }

    public Password loginPassword() {
        return password;
    }

    public Role loginRole() {
        return role;
    }

}