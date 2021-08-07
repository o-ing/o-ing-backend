package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.member.exception.InvalidArgumentException;
import kr.ac.hs.oing.member.exception.MemberExceptionMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhoneNumber {
    // Mobile Phone Identification Number :: 010
    @Transient
    private static final String PHONE_NUMBER_VALIDATOR = "^010[0-9]{8}$";

    @Column(name = "member_phone_number", nullable = false)
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void validate(String phoneNumber) {
        if (!Pattern.matches(PHONE_NUMBER_VALIDATOR, phoneNumber)) {
            throw new InvalidArgumentException(MemberExceptionMessage.PHONE_NUMBER);
        }
    }

    @Override
    public String toString() {
        if (this.phoneNumber() == null) {
            throw new InvalidArgumentException(MemberExceptionMessage.PHONE_NUMBER);
        }
        return this.phoneNumber();
    }

    public String phoneNumber() {
        return phoneNumber;
    }
}