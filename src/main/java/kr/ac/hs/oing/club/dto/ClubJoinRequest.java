package kr.ac.hs.oing.club.dto;

import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClubJoinRequest {
    private final Email email;
    private final Name name;

    public Email getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }
}
