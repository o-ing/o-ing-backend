package kr.ac.hs.oing.club.dto.bundle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClubDeleteBundle {
    private final Long id;

    public Long getId() {
        return id;
    }
}
