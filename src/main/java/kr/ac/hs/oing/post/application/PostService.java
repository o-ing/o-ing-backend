package kr.ac.hs.oing.post.application;

import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.board.infrastructure.BoardRepository;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.error.exception.NonIncludeException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.post.converter.PostConverter;
import kr.ac.hs.oing.post.domain.Post;
import kr.ac.hs.oing.post.dto.bundle.PostCreateBundle;
import kr.ac.hs.oing.post.dto.bundle.PostReadAllBundle;
import kr.ac.hs.oing.post.dto.bundle.PostReadBundle;
import kr.ac.hs.oing.post.dto.bundle.PostUpdateBundle;
import kr.ac.hs.oing.post.dto.response.PostReadAllResponse;
import kr.ac.hs.oing.post.dto.response.PostReadResponse;
import kr.ac.hs.oing.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final PostConverter postConverter;

    @Transactional
    public void create(PostCreateBundle bundle) {
        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        Club club = clubRepository.findById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        Board board = boardRepository.findById(bundle.getBoardId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_BOARD);
                });
        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        if (!club.equals(board.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        Post post = postConverter.toPost(bundle.getTitle(), bundle.getContent());
        post.add(member, board);
    }

    @Transactional
    public void update(PostUpdateBundle bundle) {
        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        Club club = clubRepository.findById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        Board board = boardRepository.findById(bundle.getBoardId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_BOARD);
                });
        Post post = postRepository.findById(bundle.getPostId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_POST);
                });
        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        if (!club.equals(board.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        if (!board.equals(post.getBoard())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_BOARD);
        }
        if (!member.equals(post.getMember())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_MEMBER);
        }
        post.update(bundle.getTitle(), bundle.getContent());
    }

    @Transactional(readOnly = true)
    public List<PostReadAllResponse> getAll(PostReadAllBundle bundle) {
        Club club = clubRepository.findById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        Board board = boardRepository.findById(bundle.getBoardId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_BOARD);
                });
        if (!club.equals(board.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }

        return postRepository.findAllByBoard(board).stream()
                .map(post ->
                        postConverter.toPostReadAllResponse(
                                post,
                                bundle.getClubId(),
                                bundle.getBoardId()
                        )
                )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostReadResponse get(PostReadBundle bundle) {
        Club club = clubRepository.findById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        Board board = boardRepository.findById(bundle.getBoardId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_BOARD);
                });
        Post post = postRepository.findById(bundle.getPostId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_POST);
                });

        if (!club.equals(board.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        if (!board.equals(post.getBoard())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_BOARD);
        }

        return postConverter.toPostReadResponse(club.getId(), post);
    }
}
