package kr.ac.hs.oing.subscription.dto.bundle;

import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import lombok.Builder;

@Builder
public class SubscriptionCreateBundle {
    private final Email email;
    private final Long clubId;
    private final Resume resume;

    public Email getEmail() {
        return email;
    }

    public Long getClubId() {
        return clubId;
    }

    public Resume getResume() {
        return resume;
    }
}
