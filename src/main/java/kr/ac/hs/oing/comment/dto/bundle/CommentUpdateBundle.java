package kr.ac.hs.oing.comment.dto.bundle;

import kr.ac.hs.oing.comment.domain.vo.Content;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class CommentUpdateBundle {
    private final Email email;
    private final Long clubId;
    private final Long boardId;
    private final Long postId;
    private final Long commentId;
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

    public Long getPostId() {
        return postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Content getContent() {
        return content;
    }
}
