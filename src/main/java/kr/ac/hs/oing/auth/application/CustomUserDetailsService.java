package kr.ac.hs.oing.auth.application;

import kr.ac.hs.oing.exception.AuthException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return new User(
                member.email().toString(),
                member.password().toString(),
                member.grantedAuthorities()
        );
    }
}
