package kr.ac.hs.oing.subscription.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubscriptionCreateBundle {
    private final Email email;
    private final Long clubId;
    private final Resume resume;
}
