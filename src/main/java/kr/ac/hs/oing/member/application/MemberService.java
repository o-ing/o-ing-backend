package kr.ac.hs.oing.member.application;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.member.converter.MemberConverter;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.dto.bundle.MemberLoginBundle;
import kr.ac.hs.oing.member.dto.request.MemberSignRequest;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberConverter memberConverter;

    @Transactional
    public void createMember(MemberSignRequest dto) {
        memberRepository.save(memberConverter.toMember(passwordEncoder, dto));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(Email email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByNickname(Nickname nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }

    @Transactional(readOnly = true)
    public MemberLoginBundle findMember(Email email) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        String clubName = Objects.isNull(member.getClub()) ? "NON_INCLUDE_CLUB" : member.getClub().getName().getName();
        return memberConverter.toMemberLoginDto(member.getNickname(), member.getRole(), clubName);
    }

    @Transactional
    public void changeRole(Email email) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        member.makeMiddleRole();
    }

    @Transactional(readOnly = true)
    public Long findClubId(Email email) {
        return memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_INCLUDE_CLUB);
                }).getClub().getId();
    }

    @Transactional(readOnly = true)
    public boolean subscribedClub(Email email) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });

        if (member.getClub() != null) {
            return true;
        }
        return false;
    }
}
