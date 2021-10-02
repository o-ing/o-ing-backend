package kr.ac.hs.oing.auth.dto;

import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Data;

@Data
public class LoginResponse {
    private final String nickname;
    private final Role role;
    private final String token;

    public LoginResponse(Nickname nickname, Role role, TokenDto tokenDto) {
        this.nickname = nickname.getNickname();
        this.role = role;
        this.token = tokenDto.getToken();
    }
}
