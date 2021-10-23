package kr.ac.hs.oing.post.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.post.application.PostService;
import kr.ac.hs.oing.post.converter.PostConverter;
import kr.ac.hs.oing.post.dto.bundle.PostCreateBundle;
import kr.ac.hs.oing.post.dto.request.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostConverter postConverter;
    private final ResponseConverter responseConverter;

    @PostMapping("/club/{clubId}/board/{boardId}/post")
    public ResponseEntity<ResponseDto> create(@PathVariable Long clubId, @PathVariable Long boardId, @RequestBody PostCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        PostCreateBundle bundle = postConverter.toPostCreateBundle(username, clubId, boardId, request);

        postService.create(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.CREATE_POST_SUCCESS);
    }
}