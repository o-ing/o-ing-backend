package kr.ac.hs.oing.club.dto.response;

import kr.ac.hs.oing.club.domain.vo.Branch;
import lombok.Builder;

@Builder
public class ClubUpdateResponse {
    private final String name;
    private final String image;
    private final String description;
    private final Branch branch;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Branch getBranch() {
        return branch;
    }
}
