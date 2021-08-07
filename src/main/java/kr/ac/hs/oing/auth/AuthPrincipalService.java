package kr.ac.hs.oing.auth;

import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.MemberRepository;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthPrincipalService implements UserDetailsService {

    private final String USER_NOT_FOUND_ERROR_MESSAGE = "해당 사용자를 찾을 수 없습니다 ";
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Email memberEmail = new Email(email);
        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
                });
        return newInstanceAuthPrincipal(member);
    }

    private AuthPrincipal newInstanceAuthPrincipal(Member member) {
        return new AuthPrincipal(createLoginMember(member));
    }

    private LoginMember createLoginMember(Member member) {
        return LoginMember.builder()
                .id(member.id())
                .email(member.email())
                .password(member.password())
                .role(member.role())
                .build();
    }

}