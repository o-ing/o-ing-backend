package kr.ac.hs.oing.comment.dto.request;

import lombok.Getter;

// request에는 final ㄴㄴㄴ
@Getter
public class CommentCreateRequest {
    private String content;
}
