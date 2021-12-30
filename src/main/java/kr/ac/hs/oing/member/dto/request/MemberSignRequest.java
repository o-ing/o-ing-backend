package kr.ac.hs.oing.member.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSignRequest {

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
}