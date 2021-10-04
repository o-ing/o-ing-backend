package kr.ac.hs.oing.auth.application;

import kr.ac.hs.oing.auth.converter.AuthConverter;
import kr.ac.hs.oing.exception.AuthException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final AuthConverter authConverter;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String inputEmail) {
        Email email = new Email(inputEmail);
        return memberRepository.findOneWithAuthoritiesByEmail(email)
                .map(authConverter::of)
                .orElseThrow(() -> new AuthException(ErrorMessage.NOT_EXIST_MEMBER));
    }
}
