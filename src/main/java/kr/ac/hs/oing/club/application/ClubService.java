package kr.ac.hs.oing.club.application;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.club.dto.CreateClubDto;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    @Transactional
    public void createClub(CreateClubDto dto) {
        clubRepository.save(Club.of(dto));
    }

    @Transactional(readOnly = true)
    public boolean existsByName(Name name) {
        return clubRepository.existsByName(name);
    }
}
