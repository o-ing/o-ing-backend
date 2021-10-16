package kr.ac.hs.oing.member.converter;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Password;
import kr.ac.hs.oing.member.domain.vo.Role;
import kr.ac.hs.oing.member.dto.MemberLoginDto;
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

    public MemberLoginDto toMemberLoginDto(Nickname nickname, Role role, String name) {
        return MemberLoginDto.builder()
                .nickname(nickname)
                .role(role)
                .name(name)
                .build();
    }
}
