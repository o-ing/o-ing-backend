package kr.ac.hs.oing.member.converter;

import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.*;
import kr.ac.hs.oing.member.dto.bundle.MemberLoginBundle;
import kr.ac.hs.oing.member.dto.bundle.MemberSignBundle;
import kr.ac.hs.oing.member.dto.request.MemberSignRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public MemberSignBundle toMemberSignBundle(MemberSignRequest request) {
        return MemberSignBundle.builder()
                .email(new Email(request.getEmail()))
                .password(new Password(request.getPassword()))
                .name(new Name(request.getName()))
                .nickname(new Nickname(request.getNickname()))
                .phoneNumber(new PhoneNumber(request.getPhoneNumber()))
                .build();
    }

    public Member toMember(PasswordEncoder passwordEncoder, MemberSignBundle memberSignDto) {
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

    public MemberLoginBundle toMemberLoginDto(Nickname nickname, Role role, String name) {
        return MemberLoginBundle.builder()
                .nickname(nickname)
                .role(role)
                .clubName(name)
                .build();
    }
}
