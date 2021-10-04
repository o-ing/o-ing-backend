package kr.ac.hs.oing.subscription.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.exception.DuplicationArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.application.MemberService;
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
    private final MemberService memberService;

    @PostMapping("/subscription")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ResponseDto> subscript(@RequestBody SubscriptionRequest request) {
        Email email = new Email(SecurityUtils.getCurrentUsername().get());

        if (memberService.subscribedClub(email)) {
            throw new DuplicationArgumentException(ErrorMessage.ALREADY_SIGN_CLUB);
        }

        subscriptionService.subscript(email, request);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CREATE_SUBSCRIPTION_SUCCESS));
    }

}
