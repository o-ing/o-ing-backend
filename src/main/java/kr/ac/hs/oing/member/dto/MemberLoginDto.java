package kr.ac.hs.oing.member.dto;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginDto {
    private Nickname nickname;
    private Role role;
    private String name;
}
