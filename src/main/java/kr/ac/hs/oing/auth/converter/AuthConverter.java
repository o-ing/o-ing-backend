package kr.ac.hs.oing.auth.converter;

import kr.ac.hs.oing.auth.dto.LoginResponse;
import kr.ac.hs.oing.auth.dto.TokenDto;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.dto.MemberLoginDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthConverter {
    public LoginResponse of(MemberLoginDto member, TokenDto token) {
        return LoginResponse.builder()
                .nickname(member.getNickname().getNickname())
                .role(member.getRole())
                .clubName(member.getName())
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
