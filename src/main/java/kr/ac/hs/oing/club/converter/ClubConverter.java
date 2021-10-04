package kr.ac.hs.oing.club.converter;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.dto.ClubDto;
import kr.ac.hs.oing.club.dto.ClubInquireResponse;
import org.springframework.stereotype.Component;

@Component
public class ClubConverter {
    public static ClubInquireResponse toClubInquireResponse(Club club) {
        return ClubInquireResponse.builder()
                .name(club.getName().getName())
                .image(club.getImage().getImage())
                .branch(club.getBranch())
                .build();
    }

    public static ClubDto toClubDto(Club club) {
        return ClubDto.builder()
                .name(club.getName().getName())
                .image(club.getImage().getImage())
                .description(club.getDescription().getDescription())
                .branch(club.getBranch())
                .build();
    }
}
