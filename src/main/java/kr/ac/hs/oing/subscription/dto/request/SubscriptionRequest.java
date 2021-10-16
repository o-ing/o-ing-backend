package kr.ac.hs.oing.subscription.dto.request;

import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.subscription.domain.vo.Resume;

public class SubscriptionRequest {
    private Name name;
    private Resume resume;

    public Name getName() {
        return name;
    }

    public Resume getResume() {
        return resume;
    }
}
