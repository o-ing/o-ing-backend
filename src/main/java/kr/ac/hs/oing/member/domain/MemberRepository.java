package kr.ac.hs.oing.member.domain;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.domain.vo.Nickname;
import kr.ac.hs.oing.member.domain.vo.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(Email email);

    boolean existsByEmail(Email email);

    boolean existsByNickname(Nickname nickname);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
