package kr.ac.hs.oing.subscription.dto.response;

import lombok.Builder;

@Builder
public class SubscriptionResponse {
    private final String name;
    private final String email;
    private final String resume;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getResume() {
        return resume;
    }
}