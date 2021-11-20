package kr.ac.hs.oing.board.dto.bundle;

import kr.ac.hs.oing.board.domain.vo.Description;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class BoardUpdateBundle {
    private final Long clubId;
    private final Long boardId;
    private final Email email;
    private final Name name;
    private final Description description;

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Email getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }
}
