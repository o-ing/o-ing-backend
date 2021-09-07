package kr.ac.hs.oing.member.application;

import kr.ac.hs.oing.exception.DuplicationArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.dto.MemberSignDto;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberSignDto dto) {
        checkDuplicationSignMember(dto);
        memberRepository.save(dto.sign(passwordEncoder));
    }

    private void checkDuplicationSignMember(MemberSignDto dto) {
        if (existsByEmail(dto.email())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_EMAIL);
        }

        if (existsByNickname(dto.nickname())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_NICKNAME);
        }

        if (existsByPhoneNumber(dto.phoneNumber())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_PHONE_NUMBER);
        }
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
}
