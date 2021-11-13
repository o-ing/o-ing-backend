package kr.ac.hs.oing.comment.presentation;

import io.swagger.annotations.ApiOperation;
import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.comment.application.CommentService;
import kr.ac.hs.oing.comment.converter.CommentConverter;
import kr.ac.hs.oing.comment.dto.bundle.CommentCreateBundle;
import kr.ac.hs.oing.comment.dto.bundle.CommentDeleteBundle;
import kr.ac.hs.oing.comment.dto.request.CommentCreateRequest;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentConverter commentConverter;
    private final CommentService commentService;
    private final ResponseConverter responseConverter;

    @ApiOperation("댓글 생성")
    @PostMapping("/club/{clubId}/board/{boardId}/post/{postId}/comment")
    public ResponseEntity<ResponseDto> create(@PathVariable Long clubId, @PathVariable Long postId, @RequestBody CommentCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        CommentCreateBundle bundle = commentConverter.toCommentCreateBundle(username, clubId, postId, request);
        commentService.create(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.CREATE_COMMENT_SUCCESS);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/club/{clubId}/board/{boardId}/post/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long clubId, @PathVariable Long boardId, @PathVariable Long postId, @PathVariable Long commentId) {
        String username = SecurityUtils.getCurrentUsername().get();

        CommentDeleteBundle bundle = commentConverter.toCommentDeleteBundle(username, clubId, boardId, postId, commentId);

        commentService.delete(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.DELETE_COMMENT_SUCCESS);
    }
}
