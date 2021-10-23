package kr.ac.hs.oing.board.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class BoardDeleteBundle {
    private final Email email;
    private final Long clubId;
    private final Long boardId;

    public Email getEmail() {
        return email;
    }

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }
}
