package kr.ac.hs.oing.board.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.board.application.BoardService;
import kr.ac.hs.oing.board.converter.BoardConverter;
import kr.ac.hs.oing.board.dto.bundle.BoardCreateBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardDeleteBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardReadBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardUpdateBundle;
import kr.ac.hs.oing.board.dto.request.BoardCreateRequest;
import kr.ac.hs.oing.board.dto.request.BoardUpdateRequest;
import kr.ac.hs.oing.board.dto.response.BoardReadResponse;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Board")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardConverter boardConverter;
    private final ResponseConverter responseConverter;

    @ApiOperation("게시판 생성")
    @PostMapping("/club/{id}/board")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> create(@PathVariable Long id, @RequestBody BoardCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        BoardCreateBundle bundle = boardConverter.toBoardCreateBundleDto(username, id, request);
        boardService.createBoard(bundle);
        return responseConverter.toResponseEntity(ResponseMessage.CREATE_BOARD_SUCCESS);
    }

    @ApiOperation("게시판 삭제")
    @DeleteMapping("/club/{clubId}/board/{boardId}")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long clubId, @PathVariable Long boardId) {
        String username = SecurityUtils.getCurrentUsername().get();
        BoardDeleteBundle bundle = boardConverter.toBoardDeleteBundle(username, clubId, boardId);

        boardService.delete(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.DELETE_BOARD_SUCCESS);
    }

    @ApiOperation("게시판 전체 조회")
    @GetMapping("/club/{clubId}/board")
    public ResponseEntity<ResponseDto> readAll(@PathVariable Long clubId) {
        BoardReadBundle bundle = new BoardReadBundle(clubId);

        List<BoardReadResponse> response = boardService.getAll(bundle);

        return responseConverter.toResponseEntity(
                ResponseMessage.READ_ALL_BOARD_SUCCESS,
                response
        );
    }

    @ApiOperation("게시판 수정")
    @PutMapping("/club/{clubId}/board/{boardId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long clubId, @PathVariable Long boardId, @RequestBody BoardUpdateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();

        BoardUpdateBundle bundle = boardConverter.toBoardUpdateBundle(clubId, boardId, username, request);

        boardService.update(bundle);

        return responseConverter.toResponseEntity(
                ResponseMessage.UPDATE_BOARD_SUCCESS
        );
    }
}
