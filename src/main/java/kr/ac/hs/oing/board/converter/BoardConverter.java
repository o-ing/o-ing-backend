package kr.ac.hs.oing.board.converter;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.domain.vo.Description;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.board.dto.bundle.BoardCreateBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardDeleteBundle;
import kr.ac.hs.oing.board.dto.request.BoardCreateRequest;
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
}
