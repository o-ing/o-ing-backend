package kr.ac.hs.oing.board.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.board.application.BoardService;
import kr.ac.hs.oing.board.converter.BoardConverter;
import kr.ac.hs.oing.board.dto.bundle.BoardCreateBundle;
import kr.ac.hs.oing.board.dto.request.BoardCreateRequest;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardConverter boardConverter;

    @PostMapping("/club/{id}/board")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> create(@PathVariable Long id, @RequestBody BoardCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        BoardCreateBundle bundle = boardConverter.toBoardCreateBundleDto(username, id, request);
        boardService.createBoard(bundle);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CREATE_BOARD_SUCCESS));
    }

}
