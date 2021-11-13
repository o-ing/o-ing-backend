package kr.ac.hs.oing.post.converter;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.post.domain.Post;
import kr.ac.hs.oing.post.domain.vo.Content;
import kr.ac.hs.oing.post.domain.vo.Title;
import kr.ac.hs.oing.post.dto.bundle.PostCreateBundle;
import kr.ac.hs.oing.post.dto.bundle.PostReadAllBundle;
import kr.ac.hs.oing.post.dto.bundle.PostUpdateBundle;
import kr.ac.hs.oing.post.dto.request.PostCreateRequest;
import kr.ac.hs.oing.post.dto.request.PostUpdateRequest;
import kr.ac.hs.oing.post.dto.response.PostReadAllResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostConverter {
    public PostCreateBundle toPostCreateBundle(String username, Long clubId, Long boardId, PostCreateRequest request) {
        return PostCreateBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
                .boardId(boardId)
                .title(new Title(request.getTitle()))
                .content(new Content(request.getContent()))
                .build();
    }

    public Post toPost(Title title, Content content) {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

    public PostUpdateBundle toPostUpdateBundle(String username, Long clubId, Long boardId, Long postId, PostUpdateRequest request) {
        return PostUpdateBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
                .boardId(boardId)
                .postId(postId)
                .title(new Title(request.getTitle()))
                .content(new Content(request.getContent()))
                .build();
    }

    public PostReadAllBundle toPostReadAllBundle(Long clubId, Long boardId) {
        return PostReadAllBundle.builder()
                .clubId(clubId)
                .boardId(boardId)
                .build();
    }

    public PostReadAllResponse toPostReadAllResponse(Post post, Long clubId, Long boardId) {
        return PostReadAllResponse.builder()
                .clubId(clubId)
                .boardId(boardId)
                .postId(post.getId())
                .title(post.getTitle().getTitle())
                .build();
    }
}