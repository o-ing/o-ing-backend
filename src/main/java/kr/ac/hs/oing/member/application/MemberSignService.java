package kr.ac.hs.oing.member.application;

import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import kr.ac.hs.oing.exception.DuplicationArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void createMember(MemberSignRequest request) {
        checkDuplicationSignMember(request);
        memberRepository.save(request.sign(passwordEncoder));
    }

    private void checkDuplicationSignMember(MemberSignRequest request) {
        if (memberService.existsByEmail(request.email())) {
            throw new DuplicationArgumentException(ErrorMessage.REDUPLICATION_EMAIL);
        }

        if (memberService.existsByNickname(request.nickname())) {
            throw new DuplicationArgumentException(ErrorMessage.REDUPLICATION_NICKNAME);
        }

        if (memberService.existsByPhoneNumber(request.phoneNumber())) {
            throw new DuplicationArgumentException(ErrorMessage.REDUPLICATION_PHONE_NUMBER);
        }
    }

}
