package kr.ac.hs.oing.club.presentation;

import kr.ac.hs.oing.auth.SecurityUtils;
import kr.ac.hs.oing.club.application.ClubService;
import kr.ac.hs.oing.club.dto.CreateClubDto;
import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.exception.DuplicationArgumentException;
import kr.ac.hs.oing.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static kr.ac.hs.oing.common.dto.ResponseMessage.CREATE_CLUB_SUCCESS;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @PostMapping("/club")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseDto> createClub(@RequestBody CreateClubDto dto) {
        if (clubService.existsByName(dto.getName())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_CLUB_NAME);
        }
        clubService.createClub(dto);
        return ResponseEntity.ok(ResponseDto.of(CREATE_CLUB_SUCCESS));
    }
}
