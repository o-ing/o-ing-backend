package kr.ac.hs.oing.club.dto;

import kr.ac.hs.oing.club.domain.vo.Branch;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClubInquireResponse {
    private final String name;
    private final String image;
    private final Branch branch;
}
