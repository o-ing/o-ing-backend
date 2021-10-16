package kr.ac.hs.oing.post.dto.request;

import lombok.Data;

@Data
public class PostCreateRequest {
    private final String title;
    private final String content;
}
