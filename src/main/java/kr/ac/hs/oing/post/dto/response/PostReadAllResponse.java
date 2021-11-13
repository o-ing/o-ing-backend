package kr.ac.hs.oing.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostReadAllResponse {
    private final Long clubId;
    private final Long boardId;
    private final Long postId;
    private final String title;
}