package kr.ac.hs.oing.subscription.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.subscription.application.SubscriptionService;
import kr.ac.hs.oing.subscription.converter.SubscriptionConverter;
import kr.ac.hs.oing.subscription.dto.bundle.SubscriptionCreateBundle;
import kr.ac.hs.oing.subscription.dto.bundle.SubscriptionInquiryBundle;
import kr.ac.hs.oing.subscription.dto.request.SubscriptionCreateRequest;
import kr.ac.hs.oing.subscription.dto.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionConverter subscriptionConverter;

    @PostMapping("/club/{clubId}/subscription")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ResponseDto> create(@PathVariable Long clubId, @RequestBody SubscriptionCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        SubscriptionCreateBundle bundle = subscriptionConverter.toSubscriptionCreateBundle(username, clubId, request);

        subscriptionService.subscript(bundle);

        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CREATE_SUBSCRIPTION_SUCCESS));
    }

    @GetMapping("/club/{clubId}/subscription")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN')")
    public ResponseEntity<ResponseDto> getAll(@PathVariable Long clubId) {
        String username = SecurityUtils.getCurrentUsername().get();

        SubscriptionInquiryBundle bundle = subscriptionConverter.toSubscriptionInquiryBundle(username, clubId);


        List<SubscriptionResponse> allSubscriptions = subscriptionService.findAllSubscriptions(bundle);

        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.SUBSCRIPTIONS_INQUIRY_SUCCESS, allSubscriptions));
    }
}
