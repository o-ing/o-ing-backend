package kr.ac.hs.oing.club.dto.request;

import kr.ac.hs.oing.club.domain.vo.Branch;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.club.domain.vo.Name;
import lombok.Data;

@Data
public class ClubCreateRequest {
    private final Name name;
    private final Image image;
    private final Branch branch;
}
