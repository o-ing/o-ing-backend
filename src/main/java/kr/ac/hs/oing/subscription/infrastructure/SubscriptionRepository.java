package kr.ac.hs.oing.subscription.infrastructure;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.subscription.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsBySubscriptionMemberAndSubscriptionClub(Member member, Club club);
}
