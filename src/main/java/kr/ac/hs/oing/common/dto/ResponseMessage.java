package kr.ac.hs.oing.common.dto;

import org.springframework.http.HttpStatus;

public enum ResponseMessage {
    SIGN_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    CREATE_CLUB_SUCCESS(HttpStatus.CREATED, "클럽 생성 성공"),
    CHANGING_MEMBER_ROLE(HttpStatus.OK, "권한 수정 성공"),
    CLUBS_SUCCESS(HttpStatus.OK, "모든 클럽 조회 성공"),
    UPDATE_CLUB_DESCRIPTION_SUCCESS(HttpStatus.OK, "클럽 소개 추가 성공"),
    CREATE_SUBSCRIPTION_SUCCESS(HttpStatus.CREATED, "자기소개서 등록 성공");


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus status() {
        return status;
    }
}
