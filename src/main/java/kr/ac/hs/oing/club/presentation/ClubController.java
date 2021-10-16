package kr.ac.hs.oing.club.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.dto.request.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.response.ClubUpdateResponse;
import kr.ac.hs.oing.club.dto.request.ClubJoinRequest;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.error.exception.DuplicationArgumentException;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final MemberService memberService;

    @PostMapping("/club")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseDto> createClub(@RequestBody ClubCreateRequest dto) {
        if (clubService.existsByName(dto.getName())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_CLUB_NAME);
        }
        clubService.create(dto);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CREATE_CLUB_SUCCESS));
    }

    @PutMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseDto> changeRole(@RequestBody ClubJoinRequest request) {
        memberService.changeRole(request.getEmail());
        clubService.addMember(request.getName(), request.getEmail());
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CHANGING_MEMBER_ROLE));
    }

    @GetMapping("/clubs")
    public ResponseEntity<ResponseDto> clubs() {
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CLUBS_INQUIRY_SUCCESS, clubService.findAllClubs()));
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<ResponseDto> club(@PathVariable Long id) {
        kr.ac.hs.oing.club.dto.response.ClubDetailResponse club = clubService.findById(id);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CLUB_INQUIRY_SUCCESS, club));
    }

    @PutMapping("/club")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> updateDescription(@RequestBody Description description) {
        Email email = new Email(SecurityUtils.getCurrentUsername().get());
        Long clubId = memberService.findClubId(email);
        ClubUpdateResponse club = clubService.updateDescription(clubId, description);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.UPDATE_CLUB_DESCRIPTION_SUCCESS, club));
    }
}
