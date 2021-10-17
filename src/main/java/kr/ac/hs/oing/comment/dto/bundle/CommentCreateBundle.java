package kr.ac.hs.oing.comment.dto.bundle;

import kr.ac.hs.oing.comment.domain.vo.Content;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class CommentCreateBundle {
    private final Email email;
    private final Long clubId;
    private final Long postId;
    private final Content content;

    public Email getEmail() {
        return email;
    }

    public Long getClubId() {
        return clubId;
    }

    public Long getPostId() {
        return postId;
    }

    public Content getContent() {
        return content;
    }
}
