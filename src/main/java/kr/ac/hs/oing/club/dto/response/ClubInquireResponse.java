package kr.ac.hs.oing.club.dto.response;

import kr.ac.hs.oing.club.domain.vo.Branch;
import lombok.Builder;

@Builder
public class ClubInquireResponse {
    private final Long id;
    private final String name;
    private final String image;
    private final Branch branch;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Branch getBranch() {
        return branch;
    }
}
