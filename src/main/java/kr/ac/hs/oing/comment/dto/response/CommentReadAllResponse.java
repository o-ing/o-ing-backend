package kr.ac.hs.oing.comment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReadAllResponse {
    private final Long clubId;
    private final Long boardId;
    private final Long postId;
    private final Long commentId;
    private final String content;
    private final String nickname;
}