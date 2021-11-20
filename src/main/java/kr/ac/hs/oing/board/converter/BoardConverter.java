package kr.ac.hs.oing.board.converter;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.domain.vo.Description;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.board.dto.bundle.BoardCreateBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardDeleteBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardUpdateBundle;
import kr.ac.hs.oing.board.dto.request.BoardCreateRequest;
import kr.ac.hs.oing.board.dto.request.BoardUpdateRequest;
import kr.ac.hs.oing.board.dto.response.BoardReadResponse;
import kr.ac.hs.oing.member.domain.vo.Email;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {
    public BoardCreateBundle toBoardCreateBundleDto(String username, Long id, BoardCreateRequest dto) {
        return BoardCreateBundle.builder()
                .email(new Email(username))
                .id(id)
                .name(new Name(dto.getName()))
                .description(new Description(dto.getDescription()))
                .build();
    }

    public Board toBoard(BoardCreateBundle bundle) {
        return Board.builder()
                .name(bundle.getName())
                .description(bundle.getDescription())
                .build();
    }

    public BoardDeleteBundle toBoardDeleteBundle(String username, Long clubId, Long boardId) {
        return BoardDeleteBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
                .boardId(boardId)
                .build();
    }

    public BoardReadResponse toBoardReadResponse(Board board) {
        return BoardReadResponse.builder()
                .clubId(board.getClub().getId())
                .boardId(board.getId())
                .name(board.getName().getName())
                .description(board.getDescription().getDescription())
                .build();
    }

    public BoardUpdateBundle toBoardUpdateBundle(Long clubId, Long boardId, String username, BoardUpdateRequest request) {
        return BoardUpdateBundle.builder()
                .clubId(clubId)
                .boardId(boardId)
                .email(new Email(username))
                .name(new Name(request.getName()))
                .description(new Description(request.getDescription()))
                .build();
    }
}
