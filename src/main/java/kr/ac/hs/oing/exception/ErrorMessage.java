package kr.ac.hs.oing.exception;

import java.util.Arrays;

public enum ErrorMessage {
    NAME(400, "이름 형식이 맞지 않습니다."),
    EMAIL(400, "이메일 형식이 맞지 않습니다."),
    NICKNAME(400, "닉네임 형식이 맞지 않습니다."),
    PHONE_NUMBER(400, "전화번호 형식이 맞지 않습니다."),
    PASSWORD(400, "비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
    REDUPLICATION_EMAIL(400, "중복된 이메일입니다."),
    REDUPLICATION_NICKNAME(400, "중복된 닉네임입니다."),
    REDUPLICATION_PHONE_NUMBER(400, "중복된 전화번호입니다."),
    IS_NOT_EXIST_MEMBER(400, "존재하지 않는 회원입니다.");

    private final int status;
    private final String message;

    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int status() {
        return status;
    }

    public String message() {
        return message;
    }

    public static ErrorMessage of(String errorMessage) {
        return Arrays.stream(values())
                .filter(e -> e.message.equals(errorMessage))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("존재하지 않는 예외"));
    }
}
