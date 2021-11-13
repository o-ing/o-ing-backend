package kr.ac.hs.oing.post.infrastructure;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    Optional<Post> findById(Long id);

    List<Post> findAllByBoard(Board board);
}
