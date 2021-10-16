package kr.ac.hs.oing.comment.dto.bundle;

import kr.ac.hs.oing.comment.domain.vo.Content;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateBundle {
    private final Email email;
    private final Long clubId;
    private final Long postId;
    private final Content content;
}
