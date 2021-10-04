package kr.ac.hs.oing.auth.dto;

import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private final String nickname;
    private final Role role;
    private final String token;
}
