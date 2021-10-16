package kr.ac.hs.oing.club.dto.response;

import kr.ac.hs.oing.club.domain.vo.Branch;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDetailResponse {
    private Long id;
    private String name;
    private String image;
    private Branch branch;
}
