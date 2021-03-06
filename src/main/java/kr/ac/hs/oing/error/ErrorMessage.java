package kr.ac.hs.oing.error;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorMessage {
    INVALID_MEMBER_NAME(HttpStatus.BAD_REQUEST, "이름 형식이 맞지 않습니다."),
    INVALID_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, "이메일 형식이 맞지 않습니다."),
    INVALID_MEMBER_NICKNAME(HttpStatus.BAD_REQUEST, "닉네임 형식이 맞지 않습니다."),
    INVALID_MEMBER_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "전화번호 형식이 맞지 않습니다."),
    INVALID_MEMBER_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
    DUPLICATION_MEMBER_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    DUPLICATION_MEMBER_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임입니다."),
    DUPLICATION_MEMBER_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "중복된 전화번호입니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "로그인 실패"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "정의되지 않은 서버 에러"),
    IS_NOT_CORRECT_JWT_SIGNATURE(HttpStatus.BAD_REQUEST, "잘못된 JWT 서명입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_token(HttpStatus.BAD_REQUEST, "지원되지 않는 JWT 토큰입니다."),
    IS_WRONG_TOKEN(HttpStatus.BAD_REQUEST, "JWT 토큰이 잘못되었습니다."),
    FAILED_TO_RESOLVED_TOKEN(HttpStatus.BAD_REQUEST, "JWT 토큰을 활성화시키지 못했습니다."),
    INVALID_CLUB_NAME(HttpStatus.BAD_REQUEST, "이름 형식이 맞지 않습니다."),
    INVALID_CLUB_DESCRIPTION(HttpStatus.BAD_REQUEST, "소개 형식이 맞지 않습니다."),
    DUPLICATION_CLUB_NAME(HttpStatus.BAD_REQUEST, "중복된 동아리 입니다."),
    INVALID_SUBSCRIPTION_RESUME(HttpStatus.BAD_REQUEST, "자기소개서 형식이 맞지 않습니다."),
    NOT_EXIST_CLUB(HttpStatus.BAD_REQUEST, "존재하지 않는 동아리입니다."),
    NOT_INCLUDE_CLUB(HttpStatus.BAD_REQUEST, "할당된 동아리가 존재하지 않습니다."),
    ALREADY_SIGN_CLUB(HttpStatus.BAD_REQUEST, "이미 동아리에 가입한 회원입니다."),
    INVALID_BOARD_NAME(HttpStatus.BAD_REQUEST, "게시판 형식이 맞지 않습니다."),
    DUPLICATION_BOARD_TITLE(HttpStatus.BAD_REQUEST, "중복된 게시판 명칭입니다."),
    NON_INCLUDE_CLUB(HttpStatus.BAD_REQUEST, "클럽에 포함되어 있지 않습니다."),
    NOT_EXIST_BOARD(HttpStatus.BAD_REQUEST, "존재하지 않는 게시판입니다."),
    INVALID_COMMENT_CONTENT(HttpStatus.BAD_REQUEST, "댓글 형식이 잘못되었습니다."),
    NOT_EXIST_POST(HttpStatus.BAD_REQUEST, "존재하지 않는 게시글입니다."),
    INVALID_CLUB_BRANCH(HttpStatus.BAD_REQUEST, "잘못된 클럽 유형입니다."),
    ALREADY_SUBSCRIPT_CLUB(HttpStatus.BAD_REQUEST, "이미 동아리에 지원서를 제출한 상태입니다."),
    FORBIDDEN_ACTION_FROM_ROLE(HttpStatus.FORBIDDEN, "회원 권한에 따른 금지"),
    NON_INCLUDE_BOARD(HttpStatus.BAD_REQUEST, "할당된 게시판이 존재하지 않습니다."),
    NON_INCLUDE_MEMBER(HttpStatus.BAD_REQUEST, "사용자의 게시글이 아닙니다."),
    NOT_EXIST_COMMENT(HttpStatus.BAD_REQUEST, "댓글이 존재하지 않습니다."),
    NON_INCLUDE_POST(HttpStatus.BAD_REQUEST, "게시글이 존재하지 않습니다."),
    NON_PERMISSION_COMMENT(HttpStatus.BAD_REQUEST, "댓글 수정에 대한 권한이 없습니다.");

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
