package kr.ac.hs.oing.board.dto.bundle;

import kr.ac.hs.oing.board.domain.vo.Description;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class BoardCreateBundle {
    private final Email email;
    private final Long id;
    private final Name name;
    private final Description description;

    public Email getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }
}
