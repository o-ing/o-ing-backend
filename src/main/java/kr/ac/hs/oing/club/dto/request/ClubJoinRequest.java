package kr.ac.hs.oing.club.dto.request;

import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Email;

public class ClubJoinRequest {
    private Email email;
    private Name name;

    public Email getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }
}
