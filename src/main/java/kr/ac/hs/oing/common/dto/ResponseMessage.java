package kr.ac.hs.oing.common.dto;

public enum ResponseMessage {
    SIGN_SUCCESS("회원가입에 성공하였습니다!");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String value() {
        return message;
    }

}
