package kr.ac.hs.oing.club.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.dto.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.ClubDto;
import kr.ac.hs.oing.club.dto.ClubJoinRequest;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.exception.DuplicationArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.member.application.MemberService;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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
        clubService.createClub(dto);
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
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.CLUBS_SUCCESS, clubService.findAllClubs()));
    }

    @PutMapping("/club")
    @PreAuthorize("hasAnyRole('ROLE_MIDDLE_ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseDto> updateDescription(@RequestBody Description description) {
        Email email = new Email(SecurityUtils.getCurrentUsername().get());
        Long clubId = memberService.findClubId(email);
        ClubDto club = clubService.updateDescription(clubId, description);
        return ResponseEntity.ok(ResponseDto.of(ResponseMessage.UPDATE_CLUB_DESCRIPTION_SUCCESS, club));
    }
}
