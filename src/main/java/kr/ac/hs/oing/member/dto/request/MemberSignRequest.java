package kr.ac.hs.oing.member.dto.request;

public class MemberSignRequest {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
