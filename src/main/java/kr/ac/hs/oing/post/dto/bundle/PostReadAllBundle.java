package kr.ac.hs.oing.post.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class PostReadAllBundle {
    private final Long clubId;
    private final Long boardId;

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }
}
