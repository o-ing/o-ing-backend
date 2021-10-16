package kr.ac.hs.oing.board.dto.bundle;

import kr.ac.hs.oing.board.domain.vo.Description;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardCreateBundle {
    private Email email;
    private Long id;
    private Name name;
    private Description description;
}
