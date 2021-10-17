package kr.ac.hs.oing.post.converter;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.post.domain.Post;
import kr.ac.hs.oing.post.domain.vo.Content;
import kr.ac.hs.oing.post.domain.vo.Title;
import kr.ac.hs.oing.post.dto.bundle.PostCreateBundle;
import kr.ac.hs.oing.post.dto.request.PostCreateRequest;
import org.springframework.stereotype.Component;

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
}
