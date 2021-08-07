package kr.ac.hs.oing.member.exception;

public enum MemberExceptionMessage {
    NAME("이름 형식이 맞지 않습니다."),
    EMAIL("이메일 형식이 맞지 않습니다."),
    NICKNAME("닉네임 형식이 맞지 않습니다."),
    PHONE_NUMBER("전화번호 형식이 맞지 않습니다."),
    PASSWORD("비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
    REDUPLICATION_EMAIL("중복된 이메일입니다."),
    REDUPLICATION_NICKNAME("중복된 닉네임입니다."),
    REDUPLICATION_PHONE_NUMBER("중복된 전화번호입니다.");

    private final String message;

    MemberExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
