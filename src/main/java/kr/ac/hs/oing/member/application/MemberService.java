package kr.ac.hs.oing.member.application;

import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.DuplicationArgumentException;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.member.converter.MemberConverter;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.dto.bundle.MemberSignBundle;
import kr.ac.hs.oing.member.dto.bundle.MemberUpdateBundle;
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

    @Transactional
    public void update(MemberUpdateBundle bundle) {
        Member member = findByEmail(bundle.getEmail());

        if (existsByNickname(bundle.getNickname()) && !member.isSameNickname(
            bundle.getNickname())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_NICKNAME);
        }

        if (existsByPhoneNumber(bundle.getPhoneNumber()) && !member.isSamePhoneNumber(
            bundle.getPhoneNumber())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_PHONE_NUMBER);
        }

        member.update(passwordEncoder, bundle);
    }

    @Transactional
    public void changeRole(Email email) {
        Member member = findByEmail(email);
        member.makeMiddleRole();
    }

    private Member findByEmail(Email email) {
        return memberRepository.findMemberByEmail(email)
            .orElseThrow(() -> new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER));
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
}
