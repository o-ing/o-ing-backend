package kr.ac.hs.oing.auth.dto;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Password;
import lombok.Data;

@Data
public class LoginRequestDto {
    private Email email;
    private Password password;
}
