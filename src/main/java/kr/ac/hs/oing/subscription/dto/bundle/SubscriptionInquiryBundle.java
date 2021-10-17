package kr.ac.hs.oing.subscription.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class SubscriptionInquiryBundle {
    private final Email email;
    private final Long clubId;

    public Email getEmail() {
        return email;
    }

    public Long getClubId() {
        return clubId;
    }
}
