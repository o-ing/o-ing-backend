package kr.ac.hs.oing.subscription.converter;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.subscription.domain.Subscription;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import kr.ac.hs.oing.subscription.dto.response.SubscriptionResponse;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {
    public Subscription toSubscription(Member member, Club club, Resume resume) {
        return Subscription.builder()
                .subscriptionMember(member)
                .subscriptionClub(club)
                .resume(resume)
                .build();
    }

    public SubscriptionResponse toSubscriptionResponse(Subscription subscription) {
        return SubscriptionResponse.builder()
                .name(subscription.getSubscriptionClub().getName().getName())
                .email(subscription.getSubscriptionMember().getEmail().getEmail())
                .resume(subscription.getResume().getResume())
                .build();
    }


}
