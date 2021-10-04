package kr.ac.hs.oing.subscription.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.subscription.application.SubscriptionService;
import kr.ac.hs.oing.subscription.dto.SubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscription")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ResponseDto> subscript(@RequestBody SubscriptionRequest request) {
        Email email = new Email(SecurityUtils.getCurrentUsername().get());
        subscriptionService.subscript(email, request);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CREATE_SUBSCRIPTION_SUCCESS));
    }

}
