package kr.ac.hs.oing.club.repository;

import kr.ac.hs.oing.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Member, Long> {
}
