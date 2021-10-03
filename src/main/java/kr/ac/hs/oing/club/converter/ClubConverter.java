package kr.ac.hs.oing.club.converter;

import kr.ac.hs.oing.club.domain.Club;
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
}
