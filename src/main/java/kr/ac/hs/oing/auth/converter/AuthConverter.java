package kr.ac.hs.oing.auth.converter;

import kr.ac.hs.oing.auth.dto.response.LoginResponse;
import kr.ac.hs.oing.auth.dto.bundle.TokenBundle;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.dto.bundle.MemberLoginBundle;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {
    public LoginResponse toLoginResponse(MemberLoginBundle member, TokenBundle token) {
        return LoginResponse.builder()
                .nickname(member.getNickname().getNickname())
                .role(member.getRole())
                .clubName(member.getName())
                .token(token.getToken())
                .build();
    }

    public User toUser(Member member) {
        return new User(
                member.getEmail().toString(),
                member.getPassword().toString(),
                member.grantedAuthorities()
        );
    }
}
