package kr.ac.hs.oing.member.ui;

import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.application.MemberSignService;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Password;
import kr.ac.hs.oing.member.domain.vo.PhoneNumber;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignService memberSignService;

    @RequestMapping({"/", ""})
    public String index() {
        return "index"; // :: TODO return 주소 바꾸기
    }

    @GetMapping("/auth/sign")
    public String sign() {
        return "/"; // :: TODO return 주소 바꾸기
    }

    @PostMapping("/auth/sign")
    public ResponseEntity createMember(@RequestBody MemberSignRequest request) {
        memberSignService.createMember(request);
        return new ResponseEntity("ok", HttpStatus.OK);
    }

}
