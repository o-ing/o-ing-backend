package kr.ac.hs.oing.auth.presentation;

import kr.ac.hs.oing.auth.JwtFilter;
import kr.ac.hs.oing.auth.dto.LoginDto;
import kr.ac.hs.oing.auth.dto.TokenDto;
import kr.ac.hs.oing.auth.infrastructure.JwtTokenProvider;
import kr.ac.hs.oing.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kr.ac.hs.oing.common.dto.ResponseMessage.LOGIN_SUCCESS;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> authorize(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(ResponseDto.of(LOGIN_SUCCESS, newToken(loginDto)));
    }

    private TokenDto newToken(LoginDto loginDto) {
        return new TokenDto(newJwtToken(loginDto));
    }

    private String newJwtToken(LoginDto loginDto) {
        return tokenProvider.createToken(newAuthentication(loginDto));
    }

    private Authentication newAuthentication(LoginDto loginDto) {
        return newAuthentication(newAuthenticationToken(loginDto));
    }

    private UsernamePasswordAuthenticationToken newAuthenticationToken(LoginDto loginDto) {
        return new UsernamePasswordAuthenticationToken(loginDto.getEmail().toString(), loginDto.getPassword().toString());
    }

    private Authentication newAuthentication(UsernamePasswordAuthenticationToken authenticationToken) {
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
