package kr.ac.hs.oing.post.dto.response;

import lombok.Builder;

@Builder
public class PostReadResponse {
    private final Long clubId;
    private final Long boardId;
    private final Long postId;
    private final Long memberId;
    private final String title;
    private final String content;

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getMemberId() {
        return memberId;
    }
}