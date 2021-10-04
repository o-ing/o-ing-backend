package kr.ac.hs.oing.club.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.club.domain.vo.Branch;
import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.dto.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.ClubInquireResponse;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.subscription.domain.Subscription;
import kr.ac.hs.oing.subscription.domain.vo.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@AllArgsConstructor
public class Club extends DateEntity {

    @JsonIgnore
    @Id
    @Column(name = "club_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    private Name name;

    @Embedded
    private Image image;

    @Embedded
    private Description description;

    @Enumerated(EnumType.STRING)
    @Column(name = "club_branch")
    private Branch branch;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Member> members = new TreeSet<>();

    @OneToMany(mappedBy = "subscriptionClub", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions = new TreeSet<>();


    protected Club() {

    }

    public void addMember(Member member) {
        members.add(member);
        member.addClub(this);
    }

    public Name getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public Description getDescription() {
        return description;
    }

    public Branch getBranch() {
        return branch;
    }

    public void updateDescription(Description description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
