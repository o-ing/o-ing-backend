package kr.ac.hs.oing.member.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberUpdateRequest {

    private String password;
    private String nickname;
    private String phoneNumber;
}