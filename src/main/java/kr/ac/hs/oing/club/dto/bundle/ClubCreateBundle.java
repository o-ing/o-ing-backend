package kr.ac.hs.oing.club.dto.bundle;

import kr.ac.hs.oing.club.domain.vo.Branch;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.club.domain.vo.Name;
import lombok.Builder;

@Builder
public class ClubCreateBundle {
    private final Name name;
    private final Image image;
    private final Branch branch;

    public Name getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public Branch getBranch() {
        return branch;
    }
}
