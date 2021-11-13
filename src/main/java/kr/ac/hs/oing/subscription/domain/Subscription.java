package kr.ac.hs.oing.subscription.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Subscription extends DateEntity {
    @JsonIgnore
    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private Resume resume;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member subscriptionMember;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_Id")
    private Club subscriptionClub;

    public void add(Member member, Club club) {
        this.subscriptionMember = member;
        this.subscriptionClub = club;
        member.getSubscriptions().add(this);
        club.getSubscriptions().add(this);
    }
}
