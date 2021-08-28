package kr.ac.hs.oing.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.common.DateEntity;
import kr.ac.hs.oing.member.domain.vo.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends DateEntity {

    @JsonIgnore
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

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

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @JsonIgnore
    @Column(name = "member_activated")
    private boolean activated;

    public boolean isActivated() {
        return activated;
    }

    public Set<Authority> authorities() {
        return authorities;
    }

    public Email email() {
        return email;
    }

    public Password password() {
        return password;
    }

}