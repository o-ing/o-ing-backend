package kr.ac.hs.oing.member.converter;

import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.*;
import kr.ac.hs.oing.member.dto.bundle.MemberLoginBundle;
import kr.ac.hs.oing.member.dto.bundle.MemberSignBundle;
import kr.ac.hs.oing.member.dto.bundle.MemberUpdateBundle;
import kr.ac.hs.oing.member.dto.request.MemberSignRequest;
import kr.ac.hs.oing.member.dto.request.MemberUpdateRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

    public MemberSignBundle toMemberSignBundle(MemberSignRequest request) {
        return MemberSignBundle.builder()
            .email(request.getEmail())
            .password(request.getPassword())
            .name(request.getName())
            .nickname(request.getNickname())
            .phoneNumber(request.getPhoneNumber())
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
            .build();
    }

    public MemberLoginBundle toMemberLoginDto(Long id, Nickname nickname, Role role, String name) {
        return MemberLoginBundle.builder()
            .id(id)
            .nickname(nickname)
            .role(role)
            .clubName(name)
            .build();
    }

    public MemberUpdateBundle toMemberUpdateBundle(String username, MemberUpdateRequest request) {
        return MemberUpdateBundle.builder()
            .email(username)
            .password(request.getPassword())
            .nickname(request.getNickname())
            .phoneNumber(request.getPhoneNumber())
            .build();
    }
}