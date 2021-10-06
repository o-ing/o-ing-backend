package kr.ac.hs.oing.subscription.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
public class Subscription extends DateEntity {
    @JsonIgnore
    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member subscriptionMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_Id")
    private Club subscriptionClub;

    protected Subscription() {

    }

    public Resume getResume() {
        return resume;
    }

    public Member getSubscriptionMember() {
        return subscriptionMember;
    }

    public Club getSubscriptionClub() {
        return subscriptionClub;
    }
}
