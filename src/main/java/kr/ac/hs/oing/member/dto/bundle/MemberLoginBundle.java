package kr.ac.hs.oing.member.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginBundle {
    private Nickname nickname;
    private Role role;
    private String name;
}
