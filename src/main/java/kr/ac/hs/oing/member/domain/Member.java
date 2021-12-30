package kr.ac.hs.oing.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.vo.*;
import kr.ac.hs.oing.member.dto.bundle.MemberUpdateBundle;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends DateEntity {

    @JsonIgnore
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Builder
    public Member(Email email, Password password, Name name,
        Nickname nickname, PhoneNumber phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.role = Role.ROLE_USER;
    }

    public Collection<GrantedAuthority> grantedAuthorities() {
        return Collections
            .singletonList(role.getGrantedAuthority());
    }

    public void makeMiddleRole() {
        this.role = Role.ROLE_MIDDLE_ADMIN;
    }

    public boolean isSameNickname(Nickname nickname) {
        return this.nickname.equals(nickname);
    }

    public boolean isSamePhoneNumber(PhoneNumber phoneNumber) {
        return this.phoneNumber.equals(phoneNumber);
    }

    public void update(PasswordEncoder encoder, MemberUpdateBundle bundle) {
        Password password = bundle.getPassword().encode(encoder);
        this.nickname = bundle.getNickname();
        this.phoneNumber = bundle.getPhoneNumber();
        this.password = password;
    }
}