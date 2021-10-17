package kr.ac.hs.oing.member.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Builder;

@Builder
public class MemberLoginBundle {
    private final Nickname nickname;
    private final Role role;
    private final String clubName;

    public Nickname getNickname() {
        return nickname;
    }

    public Role getRole() {
        return role;
    }

    public String getClubName() {
        return clubName;
    }
}
