package kr.ac.hs.oing.board.infrastructure;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.domain.vo.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByTitle(Title title);
}
