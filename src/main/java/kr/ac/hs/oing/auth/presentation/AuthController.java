package kr.ac.hs.oing.auth.presentation;

import kr.ac.hs.oing.auth.converter.AuthConverter;
import kr.ac.hs.oing.auth.dto.LoginRequest;
import kr.ac.hs.oing.auth.dto.LoginResponse;
import kr.ac.hs.oing.auth.dto.TokenDto;
import kr.ac.hs.oing.auth.infrastructure.JwtTokenProvider;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static kr.ac.hs.oing.common.dto.ResponseMessage.LOGIN_SUCCESS;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;
    private final AuthConverter authConverter;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequest dto) {
        return ResponseEntity.ok(ResponseDto.of(LOGIN_SUCCESS, loginResponse(dto)));
    }

    private LoginResponse loginResponse(LoginRequest dto) {
        TokenDto token = newToken(dto);
        MemberLoginDto member = memberService.findMember(dto.getEmail());

        return authConverter.of(member, token);
    }

    private TokenDto newToken(LoginRequest loginDto) {
        return new TokenDto(newJwtToken(loginDto));
    }

    private String newJwtToken(LoginRequest loginDto) {
        return tokenProvider.createToken(newAuthentication(loginDto));
    }

    private Authentication newAuthentication(LoginRequest loginDto) {
        return newAuthentication(newAuthenticationToken(loginDto));
    }

    private UsernamePasswordAuthenticationToken newAuthenticationToken(LoginRequest loginDto) {
        return new UsernamePasswordAuthenticationToken(
                email(loginDto),
                password(loginDto)
        );
    }

    private String email(LoginRequest loginDto) {
        return loginDto.getEmail().toString();
    }

    private String password(LoginRequest loginDto) {
        return loginDto.getPassword().toString();
    }

    private Authentication newAuthentication(UsernamePasswordAuthenticationToken authenticationToken) {
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
