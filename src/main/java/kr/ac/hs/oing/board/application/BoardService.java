package kr.ac.hs.oing.board.application;

import kr.ac.hs.oing.board.converter.BoardConverter;
import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.board.dto.bundle.BoardCreateBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardDeleteBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardReadBundle;
import kr.ac.hs.oing.board.dto.bundle.BoardUpdateBundle;
import kr.ac.hs.oing.board.dto.response.BoardReadResponse;
import kr.ac.hs.oing.board.infrastructure.BoardRepository;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.error.exception.DuplicationArgumentException;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.error.exception.NonIncludeException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final BoardConverter boardConverter;

    @Transactional
    public void createBoard(BoardCreateBundle bundle) {
        if (existsByTitle(bundle.getName())) {
            throw new DuplicationArgumentException(ErrorMessage.DUPLICATION_BOARD_TITLE);
        }
        Club club = clubRepository.findClubById(bundle.getId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });

        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });

        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        Board board = boardConverter.toBoard(bundle);
        board.addBoard(club);
    }

    private boolean existsByTitle(Name name) {
        return boardRepository.existsByName(name);
    }

    @Transactional
    public void delete(BoardDeleteBundle bundle) {
        Club club = clubRepository.findClubById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });

        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        boardRepository.deleteById(bundle.getBoardId());
    }

    @Transactional
    public List<BoardReadResponse> getAll(BoardReadBundle bundle) {
        Club club = clubRepository.findClubById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });

        return club.getBoards().stream()
                .map(boardConverter::toBoardReadResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(BoardUpdateBundle bundle) {
        Club club = clubRepository.findClubById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });

        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        Board board = boardRepository.findById(bundle.getBoardId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NON_INCLUDE_BOARD);
                });

        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }

        board.update(bundle.getName(), bundle.getDescription());
    }
}