package kr.ac.hs.oing.member.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_GUEST(new SimpleGrantedAuthority("ROLE_GUEST")),
    ROLE_USER(new SimpleGrantedAuthority("ROLE_USER")),
    ROLE_MIDDLE_ADMIN(new SimpleGrantedAuthority("ROLE_MIDDLE_ADMIN")),
    ROLE_ADMIN(new SimpleGrantedAuthority("ROLE_ADMIN"));

    private final GrantedAuthority grantedAuthority;
}