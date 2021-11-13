package kr.ac.hs.oing.member.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.converter.MemberConverter;
import kr.ac.hs.oing.member.dto.bundle.MemberSignBundle;
import kr.ac.hs.oing.member.dto.request.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("Member")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;
    private final ResponseConverter responseConverter;
    
    @ApiOperation("회원가입")
    @PostMapping("/member/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody MemberSignRequest request) {
        MemberSignBundle bundle = memberConverter.toMemberSignBundle(request);
        memberService.createMember(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.SIGN_SUCCESS);
    }
}
