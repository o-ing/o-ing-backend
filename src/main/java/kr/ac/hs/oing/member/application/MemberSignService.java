package kr.ac.hs.oing.member.application;

import kr.ac.hs.oing.member.domain.MemberRepository;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import kr.ac.hs.oing.member.exception.DuplicationArgumentException;
import kr.ac.hs.oing.member.exception.MemberExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Transactional
    public void createMember(MemberSignRequest request) {
        checkDuplicationSignMember(request);
        memberRepository.save(request.sign());
    }

    private void checkDuplicationSignMember(MemberSignRequest request) {
        if (memberService.existsByEmail(request.email())) {
            throw new DuplicationArgumentException(MemberExceptionMessage.REDUPLICATION_EMAIL);
        }

        if (memberService.existsByNickname(request.nickname())) {
            throw new DuplicationArgumentException(MemberExceptionMessage.REDUPLICATION_NICKNAME);
        }

        if (memberService.existsByPhoneNumber(request.phoneNumber())) {
            throw new DuplicationArgumentException(MemberExceptionMessage.REDUPLICATION_PHONE_NUMBER);
        }
    }

}
