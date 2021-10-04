package kr.ac.hs.oing.club.dto;

import kr.ac.hs.oing.club.domain.vo.Branch;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDto {
    private final String name; //필요 없음
    private final String image; //필요 없음
    private final String description;
    private final Branch branch; //필요 없음
}
