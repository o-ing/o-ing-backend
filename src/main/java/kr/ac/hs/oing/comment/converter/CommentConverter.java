package kr.ac.hs.oing.comment.converter;

import kr.ac.hs.oing.comment.domain.Comment;
import kr.ac.hs.oing.comment.domain.vo.Content;
import kr.ac.hs.oing.comment.dto.bundle.CommentCreateBundle;
import kr.ac.hs.oing.comment.dto.bundle.CommentDeleteBundle;
import kr.ac.hs.oing.comment.dto.bundle.CommentReadAllBundle;
import kr.ac.hs.oing.comment.dto.request.CommentCreateRequest;
import kr.ac.hs.oing.comment.dto.response.CommentReadAllResponse;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.post.domain.Post;
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

    public CommentDeleteBundle toCommentDeleteBundle(String username, Long clubId, Long boardId, Long postId, Long commentId) {
        return CommentDeleteBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
                .boardId(boardId)
                .postId(postId)
                .commentId(commentId)
                .build();
    }

    public CommentReadAllBundle toCommentReadAllBundle(Long clubId, Long boardId, Long postId) {
        return CommentReadAllBundle.builder()
                .clubId(clubId)
                .boardId(boardId)
                .postId(postId)
                .build();
    }

    public CommentReadAllResponse toCommentReadAllResponse(Comment comment, Long clubId, Long boardId) {
        return CommentReadAllResponse.builder()
                .clubId(clubId)
                .boardId(boardId)
                .postId(comment.getPost().getId())
                .commentId(comment.getId())
                .content(comment.getContent().getContent())
                .nickname(comment.getMember().getNickname().getNickname())
                .build();
    }
}
