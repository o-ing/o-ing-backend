package kr.ac.hs.oing.member.domain.vo;

import javax.persistence.Column;

public enum Role {
    GUEST("손님"),
    USER("사용자"),
    ADMIN("관리자");

    @Column(name = "member_role", nullable = false)
    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}