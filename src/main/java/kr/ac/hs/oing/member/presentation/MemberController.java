package kr.ac.hs.oing.member.presentation;


import kr.ac.hs.oing.auth.JwtFilter;
import kr.ac.hs.oing.auth.TokenProvider;
import kr.ac.hs.oing.auth.dto.TokenDto;
import kr.ac.hs.oing.common.ResponseDto;
import kr.ac.hs.oing.member.application.MemberSignService;
import kr.ac.hs.oing.member.dto.MemberLoginDto;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static kr.ac.hs.oing.common.ResponseMessage.SIGN_SUCCESS;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberSignService memberSignService;

    @PostMapping("/sign")
    public ResponseEntity<ResponseDto> createMember(@RequestBody MemberSignRequest request) {
        memberSignService.createMember(request);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, SIGN_SUCCESS));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody MemberLoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail().toString(), loginDto.getPassword().toString());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
