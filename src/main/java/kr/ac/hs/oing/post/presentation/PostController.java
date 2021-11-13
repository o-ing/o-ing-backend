package kr.ac.hs.oing.post.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.post.application.PostService;
import kr.ac.hs.oing.post.converter.PostConverter;
import kr.ac.hs.oing.post.dto.bundle.PostCreateBundle;
import kr.ac.hs.oing.post.dto.bundle.PostReadAllBundle;
import kr.ac.hs.oing.post.dto.bundle.PostUpdateBundle;
import kr.ac.hs.oing.post.dto.request.PostCreateRequest;
import kr.ac.hs.oing.post.dto.request.PostUpdateRequest;
import kr.ac.hs.oing.post.dto.response.PostReadAllResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/club/{clubId}/board/{boardId}/post/{postId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long clubId, @PathVariable Long boardId, @PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();

        PostUpdateBundle bundle = postConverter.toPostUpdateBundle(username, clubId, boardId, postId, request);

        postService.update(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.UPDATE_POST_SUCCESS);
    }

    @GetMapping("/club/{clubId}/board/{boardId}/post")
    public ResponseEntity<ResponseDto> readAll(@PathVariable Long clubId, @PathVariable Long boardId) {
        PostReadAllBundle bundle = postConverter.toPostReadAllBundle(clubId, boardId);

        List<PostReadAllResponse> response = postService.getAll(bundle);

        return responseConverter.toResponseEntity(
                ResponseMessage.READ_ALL_POST_SUCCESS,
                response
        );
    }
}
