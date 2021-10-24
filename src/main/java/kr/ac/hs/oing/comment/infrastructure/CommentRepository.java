package kr.ac.hs.oing.comment.infrastructure;

import kr.ac.hs.oing.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    void deleteById(Long id);
}
