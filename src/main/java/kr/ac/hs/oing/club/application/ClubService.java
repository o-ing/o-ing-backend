package kr.ac.hs.oing.club.application;

import kr.ac.hs.oing.club.converter.ClubConverter;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.club.dto.bundle.ClubCreateBundle;
import kr.ac.hs.oing.club.dto.response.ClubDetailResponse;
import kr.ac.hs.oing.club.dto.response.ClubUpdateResponse;
import kr.ac.hs.oing.club.dto.response.ClubInquireResponse;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.DuplicationArgumentException;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubConverter clubConverter;

    @Transactional
    public void create(ClubCreateBundle bundle) {
        if (existsByName(bundle.getName())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_CLUB_NAME);
        }
        Club club = clubConverter.toClub(bundle);
        clubRepository.save(club);
    }

    private boolean existsByName(Name name) {
        return clubRepository.existsByName(name);
    }

    @Transactional
    public void addMember(Name name, Email email) {
        Club club = clubRepository.findClubByName(name)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        club.addMember(member);
    }

    @Transactional(readOnly = true)
    public List<ClubInquireResponse> findAllClubs() {
        return clubRepository.findAll()
                .stream()
                .map(clubConverter::toClubInquireResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClubUpdateResponse updateDescription(Long id, Description description) {
        Club club = clubRepository.findClubById(id)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        club.updateDescription(description);

        return clubConverter.toClubDto(club);
    }

    @Transactional(readOnly = true)
    public ClubDetailResponse findById(Long id) {
        Club club = clubRepository.findClubById(id)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        return clubConverter.toClubDetailResponse(club);
    }
}
