package kr.ac.hs.oing.member.presentation;


import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.member.application.MemberSignService;
import kr.ac.hs.oing.member.dto.MemberSignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kr.ac.hs.oing.common.dto.ResponseMessage.SIGN_SUCCESS;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignService memberSignService;

    @PostMapping("/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody MemberSignDto request) {
        memberSignService.createMember(request);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, SIGN_SUCCESS));
    }
}