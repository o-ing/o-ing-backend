package kr.ac.hs.oing.member.presentation;

import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.converter.MemberConverter;
import kr.ac.hs.oing.member.dto.bundle.MemberSignBundle;
import kr.ac.hs.oing.member.dto.request.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kr.ac.hs.oing.common.dto.ResponseMessage.SIGN_SUCCESS;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @PostMapping("/member/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody MemberSignRequest request) {
        MemberSignBundle bundle = memberConverter.toMemberSignBundle(request);
        memberService.createMember(bundle);
        return ResponseEntity.ok(ResponseDto.of(SIGN_SUCCESS));
    }
}
