package kr.ac.hs.oing.common;

public enum ResponseMessage {
    SIGN_SUCCESS("회원가입 성공");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String value() {
        return message;
    }

}
