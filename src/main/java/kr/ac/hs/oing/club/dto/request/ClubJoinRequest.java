package kr.ac.hs.oing.club.dto.request;

import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Data;

@Data
public class ClubJoinRequest {
    private final Email email;
    private final Name name;
}
