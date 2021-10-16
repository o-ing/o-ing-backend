package kr.ac.hs.oing.board.dto.bundle;

import kr.ac.hs.oing.board.domain.vo.Content;
import kr.ac.hs.oing.board.domain.vo.Title;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardCreateBundle {
    private Email email;
    private Long id;
    private Title title;
    private Content content;
}
