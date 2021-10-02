package kr.ac.hs.oing.club.application;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.club.dto.ClubCreateRequest;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.exception.ErrorMessage;
import kr.ac.hs.oing.exception.NonExitsException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.domain.vo.Email;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createClub(ClubCreateRequest dto) {
        clubRepository.save(Club.of(dto));
    }

    @Transactional(readOnly = true)
    public boolean existsByName(Name name) {
        return clubRepository.existsByName(name);
    }

    @Transactional
    public void addMember(Name name, Email email) {
        Club club = clubRepository.findClubByName(name)
                .orElseThrow(() -> {
                    throw new RuntimeException("");
                });
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        club.addMember(member);
    }
}
