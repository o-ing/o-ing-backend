package kr.ac.hs.oing.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.vo.*;
import kr.ac.hs.oing.member.dto.MemberSignRequest;
import kr.ac.hs.oing.subscription.domain.Subscription;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@AllArgsConstructor
public class Member extends DateEntity {

    @JsonIgnore
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    private Email email;

    @JsonIgnore
    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_Id")
    private Club club;

    @OneToMany(mappedBy = "subscriptionMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions = new TreeSet<>();

    protected Member() {

    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Role getRole() {
        return role;
    }

    public Collection<GrantedAuthority> grantedAuthorities() {
        return Collections
                .singletonList(role.getGrantedAuthority());
    }

    public void makeMiddleRole() {
        this.role = Role.ROLE_MIDDLE_ADMIN;
    }

    public void addClub(Club club) {
        this.club = club;
    }

    public Club getClub() {
        return club;
    }
    
    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }
}