package kr.ac.hs.oing.subscription.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponse {
    private final String name;
    private final String email;
    private final String resume;
}