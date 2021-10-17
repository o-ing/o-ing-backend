package kr.ac.hs.oing.member.application;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.DuplicationArgumentException;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.member.converter.MemberConverter;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.dto.bundle.MemberLoginBundle;
import kr.ac.hs.oing.member.dto.bundle.MemberSignBundle;
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
    public void createMember(MemberSignBundle bundle) {
        if (existsByEmail(bundle.getEmail())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_EMAIL);
        }

        if (existsByNickname(bundle.getNickname())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_NICKNAME);
        }

        if (existsByPhoneNumber(bundle.getPhoneNumber())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_PHONE_NUMBER);
        }

        memberRepository.save(memberConverter.toMember(passwordEncoder, bundle));
    }

    private boolean existsByEmail(Email email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean existsByNickname(Nickname nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    private boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
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
}
