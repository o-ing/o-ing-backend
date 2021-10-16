package kr.ac.hs.oing.subscription.application;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.subscription.converter.SubscriptionConverter;
import kr.ac.hs.oing.subscription.domain.Subscription;
import kr.ac.hs.oing.subscription.dto.request.SubscriptionRequest;
import kr.ac.hs.oing.subscription.infrastructure.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final SubscriptionConverter subscriptionConverter;

    @Transactional
    public void subscript(Email email, SubscriptionRequest request) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });

        Club club = clubRepository.findClubByName(request.getName()).orElseThrow(() -> {
            throw new RuntimeException("");
        });

        Subscription subscription = subscriptionConverter.toSubscription(member, club, request.getResume());
        club.addSubscription(subscription);
        member.addSubscription(subscription);

        subscriptionRepository.save(subscription);
    }
}
