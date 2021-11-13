package kr.ac.hs.oing.post.dto.bundle;

import lombok.Builder;

@Builder
public class PostReadBundle {
    private final Long clubId;
    private final Long boardId;
    private final Long postId;

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getPostId() {
        return postId;
    }
}
