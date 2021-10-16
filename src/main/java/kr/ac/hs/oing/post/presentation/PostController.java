package kr.ac.hs.oing.post.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.post.application.PostService;
import kr.ac.hs.oing.post.converter.PostConverter;
import kr.ac.hs.oing.post.dto.bundle.PostCreateBundle;
import kr.ac.hs.oing.post.dto.request.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostConverter postConverter;

    @PostMapping("/club/{clubId}/board/{boardId}/post")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> create(@PathVariable Long clubId, @PathVariable Long boardId, @RequestBody PostCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        PostCreateBundle bundle = postConverter.toPostCreateBundle(username, clubId, boardId, request);

        postService.create(bundle);

        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CREATE_POST_SUCCESS));
    }
}
