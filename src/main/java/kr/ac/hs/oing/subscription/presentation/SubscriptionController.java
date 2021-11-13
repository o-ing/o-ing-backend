package kr.ac.hs.oing.subscription.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
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

@Api("Subscription")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionConverter subscriptionConverter;
    private final ResponseConverter responseConverter;

    @ApiOperation("자기소개서 등록")
    @PostMapping("/club/{clubId}/subscription")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ResponseDto> create(@PathVariable Long clubId, @RequestBody SubscriptionCreateRequest request) {
        String username = SecurityUtils.getCurrentUsername().get();
        SubscriptionCreateBundle bundle = subscriptionConverter.toSubscriptionCreateBundle(username, clubId, request);

        subscriptionService.subscript(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.CREATE_SUBSCRIPTION_SUCCESS);
    }

    @ApiOperation("자기소개서 전체 조회")
    @GetMapping("/club/{clubId}/subscription")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN')")
    public ResponseEntity<ResponseDto> getAll(@PathVariable Long clubId) {
        String username = SecurityUtils.getCurrentUsername().get();

        SubscriptionInquiryBundle bundle = subscriptionConverter.toSubscriptionInquiryBundle(username, clubId);

        List<SubscriptionResponse> subscriptions = subscriptionService.findAllSubscriptions(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.SUBSCRIPTIONS_INQUIRY_SUCCESS, subscriptions);
    }
}
