package kr.ac.hs.oing.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorMessage {
    INVALID_NAME(HttpStatus.BAD_REQUEST, "이름 형식이 맞지 않습니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "이메일 형식이 맞지 않습니다."),
    INVALID_NICKNAME(HttpStatus.BAD_REQUEST, "닉네임 형식이 맞지 않습니다."),
    INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "전화번호 형식이 맞지 않습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
    DUPLICATION_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    DUPLICATION_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임입니다."),
    DUPLICATION_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "중복된 전화번호입니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "로그인 실패"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "정의되지 않은 서버 에러");

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus status() {
        return status;
    }

    public String message() {
        return message;
    }


    public static ErrorMessage of(String errorMessage) {
        return Arrays.stream(values())
                .filter(e -> e.message.equals(errorMessage))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Non Existent Exception"));
    }
}
