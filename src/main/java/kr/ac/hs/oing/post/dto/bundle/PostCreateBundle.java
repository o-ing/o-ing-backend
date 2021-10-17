package kr.ac.hs.oing.post.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.post.domain.vo.Content;
import kr.ac.hs.oing.post.domain.vo.Title;
import lombok.Builder;

@Builder
public class PostCreateBundle {
    private final Email email;
    private final Long clubId;
    private final Long boardId;
    private final Title title;
    private final Content content;

    public Email getEmail() {
        return email;
    }

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }
}
