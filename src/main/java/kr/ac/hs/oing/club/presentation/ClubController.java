package kr.ac.hs.oing.club.presentation;

import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.dto.ClubCreateRequest;
import kr.ac.hs.oing.club.dto.ClubInquireResponse;
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

import java.util.List;

import static kr.ac.hs.oing.common.dto.ResponseMessage.CREATE_CLUB_SUCCESS;

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
        return ResponseEntity.ok(ResponseDto.of(CREATE_CLUB_SUCCESS));
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
        return ResponseEntity.ok(
                ResponseDto.of(
                        ResponseMessage.CLUBS_SUCCESS,
                        clubService.findAllClubs()
                )
        );
    }
}
