package kr.ac.hs.oing.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.vo.*;
import kr.ac.hs.oing.member.dto.MemberSignDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Collection<GrantedAuthority> grantedAuthorities() {
        return Collections
                .singletonList(role.getGrantedAuthority());
    }

    public static Member of(PasswordEncoder passwordEncoder, MemberSignDto memberSignDto) {
        Password password = memberSignDto.getPassword().encode(passwordEncoder);
        return Member.builder()
                .email(memberSignDto.getEmail())
                .password(password)
                .name(memberSignDto.getName())
                .nickname(memberSignDto.getNickname())
                .phoneNumber(memberSignDto.getPhoneNumber())
                .role(Role.USER)
                .build();
    }

}