package kr.ac.hs.oing.subscription.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubscriptionInquiryBundle {
    private final Email email;
    private final Long clubId;
}
