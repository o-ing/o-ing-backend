package kr.ac.hs.oing.member.presentation;

import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.exception.DuplicationArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kr.ac.hs.oing.common.dto.ResponseMessage.SIGN_SUCCESS;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody MemberSignRequest dto) {
        if (memberService.existsByEmail(dto.getEmail())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_EMAIL);
        }

        if (memberService.existsByNickname(dto.getNickname())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_NICKNAME);
        }

        if (memberService.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_MEMBER_PHONE_NUMBER);
        }
        memberService.createMember(dto);
        return ResponseEntity.ok(ResponseDto.of(SIGN_SUCCESS));
    }
}
