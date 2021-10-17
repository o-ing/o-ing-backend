package kr.ac.hs.oing.subscription.converter;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.subscription.domain.Subscription;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import kr.ac.hs.oing.subscription.dto.bundle.SubscriptionCreateBundle;
import kr.ac.hs.oing.subscription.dto.bundle.SubscriptionInquiryBundle;
import kr.ac.hs.oing.subscription.dto.request.SubscriptionCreateRequest;
import kr.ac.hs.oing.subscription.dto.response.SubscriptionResponse;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionConverter {
    public SubscriptionCreateBundle toSubscriptionCreateBundle(String username, Long clubId, SubscriptionCreateRequest request) {
        return SubscriptionCreateBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
                .resume(new Resume(request.getResume()))
                .build();
    }

    public Subscription toSubscription(Resume resume) {
        return Subscription.builder()
                .resume(resume)
                .build();
    }

    public SubscriptionInquiryBundle toSubscriptionInquiryBundle(String username, Long clubId) {
        return SubscriptionInquiryBundle.builder()
                .email(new Email(username))
                .clubId(clubId)
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
