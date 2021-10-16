package kr.ac.hs.oing.comment.converter;

import kr.ac.hs.oing.comment.domain.Comment;
import kr.ac.hs.oing.comment.domain.vo.Content;
import kr.ac.hs.oing.comment.dto.bundle.CommentCreateBundle;
import kr.ac.hs.oing.comment.dto.request.CommentCreateRequest;
import kr.ac.hs.oing.member.domain.vo.Email;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public CommentCreateBundle toCommentCreateBundle(String username, Long clubId, Long postId, CommentCreateRequest request) {
        return CommentCreateBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
                .postId(postId)
                .content(new Content(request.getContent()))
                .build();
    }

    public Comment toComment(Content content) {
        return Comment.builder()
                .content(content)
                .build();
    }
}
