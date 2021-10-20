package kr.ac.hs.oing.club.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.club.converter.ClubConverter;
import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.dto.bundle.ClubCreateBundle;
import kr.ac.hs.oing.club.dto.request.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.response.ClubDetailResponse;
import kr.ac.hs.oing.club.dto.response.ClubInquireResponse;
import kr.ac.hs.oing.club.dto.response.ClubUpdateResponse;
import kr.ac.hs.oing.club.dto.request.ClubJoinRequest;
import kr.ac.hs.oing.common.converter.ResponseConverter;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final MemberService memberService;
    private final ClubConverter clubConverter;
    private final ResponseConverter responseConverter;

    @PostMapping("/club")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseDto> createClub(@RequestBody ClubCreateRequest request) {
        ClubCreateBundle bundle = clubConverter.toClubCreateBundle(request);
        clubService.create(bundle);

        return responseConverter.toResponseEntity(ResponseMessage.CREATE_CLUB_SUCCESS);
    }

    @PutMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseDto> changeRole(@RequestBody ClubJoinRequest request) {
        memberService.changeRole(request.getEmail());
        clubService.addMember(request.getName(), request.getEmail());

        return responseConverter.toResponseEntity(ResponseMessage.CHANGING_MEMBER_ROLE);
    }

    @GetMapping("/clubs")
    public ResponseEntity<ResponseDto> clubs() {
        List<ClubInquireResponse> clubs = clubService.findAllClubs();
        return responseConverter.toResponseEntity(ResponseMessage.CLUBS_INQUIRY_SUCCESS, clubs);
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<ResponseDto> club(@PathVariable Long id) {
        ClubDetailResponse club = clubService.findById(id);
        return responseConverter.toResponseEntity(ResponseMessage.CLUB_INQUIRY_SUCCESS, club);
    }

    @PutMapping("/club")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> updateDescription(@RequestBody Description description) {
        Email email = new Email(SecurityUtils.getCurrentUsername().get());
        Long clubId = memberService.findClubId(email);
        ClubUpdateResponse club = clubService.updateDescription(clubId, description);

        return responseConverter.toResponseEntity(ResponseMessage.UPDATE_CLUB_DESCRIPTION_SUCCESS, club);
    }
}
