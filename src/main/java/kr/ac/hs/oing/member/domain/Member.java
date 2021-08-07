package kr.ac.hs.oing.member.domain;

import kr.ac.hs.oing.common.DateEntity;
import kr.ac.hs.oing.member.domain.vo.*;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends DateEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;


    public int id() {
        return id;
    }

    public Email email() {
        return email;
    }

    public Password password() {
        return password;
    }

    public Name name() {
        return name;
    }

    public Nickname nickname() {
        return nickname;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Role role() {
        return role;
    }
}