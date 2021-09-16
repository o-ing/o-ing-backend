package kr.ac.hs.oing.club.domain.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Branch {
    PERFORMING_ARTS("공연예술"),
    ACTING_ARTS("연행예술"),
    EXERCISE("운동"),
    EXHIBITION("전시"),
    ACADEMIC("학술"),
    ENVIRONMENT("환경"),
    RELIGION("종교");

    private final String branch;

    public String getBranch() {
        return branch;
    }
}
