package kr.ac.hs.oing.member.ui;


import kr.ac.hs.oing.common.ResponseDto;
import kr.ac.hs.oing.member.application.MemberSignService;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kr.ac.hs.oing.common.ResponseMessage.SIGN_SUCCESS;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignService memberSignService;

    @PostMapping("/auth/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody MemberSignRequest request) {
        memberSignService.createMember(request);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, SIGN_SUCCESS));
    }
}
