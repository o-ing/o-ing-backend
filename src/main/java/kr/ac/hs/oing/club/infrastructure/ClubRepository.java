package kr.ac.hs.oing.club.infrastructure;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    boolean existsByName(Name name);
}
