package kr.ac.hs.oing.subscription.application;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.AlreadyExitsException;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.error.exception.NonIncludeException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.subscription.converter.SubscriptionConverter;
import kr.ac.hs.oing.subscription.domain.Subscription;
import kr.ac.hs.oing.subscription.dto.bundle.SubscriptionCreateBundle;
import kr.ac.hs.oing.subscription.dto.bundle.SubscriptionInquiryBundle;
import kr.ac.hs.oing.subscription.dto.response.SubscriptionResponse;
import kr.ac.hs.oing.subscription.infrastructure.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final SubscriptionConverter subscriptionConverter;

    @Transactional
    public void subscript(SubscriptionCreateBundle bundle) {
        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });

        Club club = clubRepository.findClubById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });

        if (club.equals(member.getClub())) {
            throw new AlreadyExitsException(ErrorMessage.ALREADY_SIGN_CLUB);
        }

        if (existsByMemberAndClub(member, club)) {
            throw new AlreadyExitsException(ErrorMessage.ALREADY_SUBSCRIPT_CLUB);
        }

        Subscription subscription = subscriptionConverter.toSubscription(bundle.getResume());
        subscription.add(member, club);

        subscriptionRepository.save(subscription);
    }

    private boolean existsByMemberAndClub(Member member, Club club) {
        return subscriptionRepository.existsBySubscriptionMemberAndSubscriptionClub(member, club);
    }

    @Transactional(readOnly = true)
    public List<SubscriptionResponse> findAllSubscriptions(SubscriptionInquiryBundle bundle) {
        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });

        Club club = clubRepository.findClubById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });

        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }

        return club.getSubscriptions().stream()
                .map(subscriptionConverter::toSubscriptionResponse)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}