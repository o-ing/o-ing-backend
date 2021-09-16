package kr.ac.hs.oing.club.dto;

import kr.ac.hs.oing.club.domain.vo.Branch;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.club.domain.vo.Introduce;
import kr.ac.hs.oing.club.domain.vo.Name;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateClubDto {
    private final Name name;
    private final Image image;
    private final Introduce introduce;
    private final Branch branch;

    public Name getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public Introduce getIntroduce() {
        return introduce;
    }

    public Branch getBranch() {
        return branch;
    }
}
