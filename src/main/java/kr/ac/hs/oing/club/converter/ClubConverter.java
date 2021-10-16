package kr.ac.hs.oing.club.converter;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.dto.request.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.response.ClubUpdateResponse;
import kr.ac.hs.oing.club.dto.response.ClubInquireResponse;
import org.springframework.stereotype.Component;

@Component
public class ClubConverter {
    public ClubInquireResponse toClubInquireResponse(Club club) {
        return ClubInquireResponse.builder()
                .id(club.getId())
                .name(club.getName().getName())
                .image(club.getImage().getImage())
                .branch(club.getBranch())
                .build();
    }

    public ClubUpdateResponse toClubDto(Club club) {
        return ClubUpdateResponse.builder()
                .name(club.getName().getName())
                .image(club.getImage().getImage())
                .description(club.getDescription().getDescription())
                .branch(club.getBranch())
                .build();
    }

    public Club of(ClubCreateRequest createClubDto) {
        return Club.builder()
                .name(createClubDto.getName())
                .image(createClubDto.getImage())
                .branch(createClubDto.getBranch())
                .build();
    }

    public kr.ac.hs.oing.club.dto.response.ClubDetailResponse toClubDetailResponse(Club club) {
        return kr.ac.hs.oing.club.dto.response.ClubDetailResponse.builder()
                .id(club.getId())
                .name(club.getName().getName())
                .image(club.getImage().getImage())
                .branch(club.getBranch())
                .build();
    }
}
