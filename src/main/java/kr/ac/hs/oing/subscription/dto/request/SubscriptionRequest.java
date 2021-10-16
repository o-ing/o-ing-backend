package kr.ac.hs.oing.subscription.dto.request;

import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubscriptionRequest {
    private final Name name;
    private final Resume resume;

    public Name getName() {
        return name;
    }

    public Resume getResume() {
        return resume;
    }
}
