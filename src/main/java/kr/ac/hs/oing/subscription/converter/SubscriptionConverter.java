package kr.ac.hs.oing.subscription.converter;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.subscription.domain.Subscription;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {
    public static Subscription of(Member member, Club club, Resume resume) {
        return Subscription.builder()
                .subscriptionMember(member)
                .subscriptionClub(club)
                .resume(resume)
                .build();
    }
}
