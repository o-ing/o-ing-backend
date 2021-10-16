package kr.ac.hs.oing.board.converter;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.domain.vo.Content;
import kr.ac.hs.oing.board.domain.vo.Title;
import kr.ac.hs.oing.board.dto.bundle.BoardCreateBundle;
import kr.ac.hs.oing.board.dto.request.BoardCreateRequest;
import kr.ac.hs.oing.member.domain.vo.Email;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {
    public BoardCreateBundle toBoardCreateBundleDto(String username, Long id, BoardCreateRequest dto) {
        return BoardCreateBundle.builder()
                .email(new Email(username))
                .id(id)
                .title(new Title(dto.getTitle()))
                .content(new Content(dto.getContent()))
                .build();
    }

    public Board toBoard(BoardCreateBundle bundle) {
        return Board.builder()
                .title(bundle.getTitle())
                .content(bundle.getContent())
                .build();
    }
}
