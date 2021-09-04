package kr.ac.hs.oing.member.presentation;


import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.dto.MemberSignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kr.ac.hs.oing.common.dto.ResponseMessage.SIGN_SUCCESS;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody @Valid MemberSignDto request) {
        memberService.createMember(request);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, SIGN_SUCCESS));
    }
}
