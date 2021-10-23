package kr.ac.hs.oing.board.infrastructure;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.domain.vo.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByName(Name name);

    @Override
    void deleteById(Long id);
}
