package kr.ac.hs.oing.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.MemberRepository;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthPrincipalService implements UserDetailsService {

    private final String USER_NOT_FOUND_ERROR_MESSAGE = "해당 사용자를 찾을 수 없습니다 ";
    private final MemberRepository memberRepository;


    // ::TODO Login시 email과 password Parsing 수정 필요... 읽어오지를 못함..
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(newInstanceEmail(email))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
                });
        return newInstanceAuthPrincipal(member);
    }

    private Email newInstanceEmail(String email) {
        return new Email(email);
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