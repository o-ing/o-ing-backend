package kr.ac.hs.oing.club.converter;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Branch;
import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.club.dto.bundle.ClubCreateBundle;
import kr.ac.hs.oing.club.dto.bundle.ClubDeleteBundle;
import kr.ac.hs.oing.club.dto.request.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.response.ClubDetailResponse;
import kr.ac.hs.oing.club.dto.response.ClubUpdateResponse;
import kr.ac.hs.oing.club.dto.response.ClubInquireResponse;
import org.springframework.stereotype.Component;

@Component
public class ClubConverter {
    public ClubCreateBundle toClubCreateBundle(ClubCreateRequest request) {
        return ClubCreateBundle.builder()
                .name(new Name(request.getName()))
                .image(new Image(request.getImage()))
                .branch(Branch.valueOf(request.getBranch()))
                .build();
    }

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

    public Club toClub(ClubCreateBundle bundle) {
        return Club.builder()
                .name(bundle.getName())
                .image(bundle.getImage())
                .branch(bundle.getBranch())
                .description(new Description("### Club 소개 화면입니다."))
                .build();
    }

    public ClubDetailResponse toClubDetailResponse(Club club) {
        return ClubDetailResponse.builder()
                .id(club.getId())
                .name(club.getName().getName())
                .image(club.getImage().getImage())
                .description(club.getDescription().getDescription())
                .branch(club.getBranch())
                .build();
    }

    public ClubDeleteBundle toClubDeleteBundle(Long clubId) {
        return new ClubDeleteBundle(clubId);
    }
}
