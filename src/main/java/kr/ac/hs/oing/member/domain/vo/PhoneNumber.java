package kr.ac.hs.oing.member.domain.vo;

import kr.ac.hs.oing.exception.InvalidArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
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
            throw new InvalidArgumentException(ErrorMessage.INVALID_PHONE_NUMBER);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}