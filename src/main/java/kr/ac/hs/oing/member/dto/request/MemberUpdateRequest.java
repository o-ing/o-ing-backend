package kr.ac.hs.oing.member.dto.request;

public class MemberUpdateRequest {
    private String password;
    private String nickname;
    private String phoneNumber;

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
