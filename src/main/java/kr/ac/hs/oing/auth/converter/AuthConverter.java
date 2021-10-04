package kr.ac.hs.oing.auth.converter;

import kr.ac.hs.oing.auth.dto.LoginResponse;
import kr.ac.hs.oing.auth.dto.TokenDto;
import kr.ac.hs.oing.member.domain.Member;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {
    public LoginResponse of(Member member, TokenDto token) {
        return LoginResponse.builder()
                .nickname(member.getNickname().getNickname())
                .role(member.getRole())
                .token(token.getToken())
                .build();
    }

    public User of(Member member) {
        return new User(
                member.getEmail().toString(),
                member.getPassword().toString(),
                member.grantedAuthorities()
        );
    }
}
