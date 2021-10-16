package kr.ac.hs.oing.subscription.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.error.exception.DuplicationArgumentException;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.subscription.application.SubscriptionService;
import kr.ac.hs.oing.subscription.dto.request.SubscriptionRequest;
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
    private final ClubService clubService;
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

    // TODO :: 자기소개서 전체 출력
    @GetMapping("/subscriptions")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN')")
    public ResponseEntity<ResponseDto> subscriptions() {
        Email email = new Email(SecurityUtils.getCurrentUsername().get());
        Long clubId = memberService.findClubId(email);
        List<SubscriptionResponse> allSubscriptions = clubService.findAllSubscriptions(clubId);

        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.SUBSCRIPTIONS_INQUIRY_SUCCESS, allSubscriptions));
    }
}
