package kr.ac.hs.oing.auth.application;

import kr.ac.hs.oing.exception.ActivateArgumentException;
import kr.ac.hs.oing.exception.AuthException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.domain.Authority;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String inputEmail) {
        Email email = new Email(inputEmail);
        return memberRepository.findOneWithAuthoritiesByEmail(email)
                .map(this::createMember)
                .orElseThrow(() -> new AuthException(ErrorMessage.IS_NOT_EXIST_MEMBER));
    }

    private User createMember(Member member) {
        if (!member.isActivated()) {
            throw new ActivateArgumentException(ErrorMessage.IS_NOT_ACTIVATE_MEMBER);
        }
        return getUser(member, grantedAuthorities(member));
    }

    private Collection<GrantedAuthority> grantedAuthorities(Member member) {
        return member.authorities().stream()
                .map(this::simpleGrantedAuthority)
                .collect(Collectors.toList());
    }

    private SimpleGrantedAuthority simpleGrantedAuthority(Authority authority) {
        return new SimpleGrantedAuthority(authority.getAuthorityName());
    }

    private User getUser(Member member, Collection<GrantedAuthority> grantedAuthorities) {
        return new User(
                member.email().toString(),
                member.password().toString(),
                grantedAuthorities
        );
    }
}
