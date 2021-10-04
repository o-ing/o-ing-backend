package kr.ac.hs.oing.member.converter;

import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Password;
import kr.ac.hs.oing.member.domain.vo.Role;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public Member of(PasswordEncoder passwordEncoder, MemberSignRequest memberSignDto) {
        Password password = memberSignDto.getPassword().encode(passwordEncoder);
        return Member.builder()
                .email(memberSignDto.getEmail())
                .password(password)
                .name(memberSignDto.getName())
                .nickname(memberSignDto.getNickname())
                .phoneNumber(memberSignDto.getPhoneNumber())
                .role(Role.ROLE_USER)
                .build();
    }
}
