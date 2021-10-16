package kr.ac.hs.oing.comment.application;

import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.club.infrastructure.ClubRepository;
import kr.ac.hs.oing.comment.converter.CommentConverter;
import kr.ac.hs.oing.comment.domain.Comment;
import kr.ac.hs.oing.comment.dto.bundle.CommentCreateBundle;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.exception.NonExitsException;
import kr.ac.hs.oing.error.exception.NonIncludeException;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.member.infrastructure.MemberRepository;
import kr.ac.hs.oing.post.domain.Post;
import kr.ac.hs.oing.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentConverter commentConverter;
    private final ClubRepository clubRepository;

    @Transactional
    public void create(CommentCreateBundle bundle) {
        Member member = memberRepository.findMemberByEmail(bundle.getEmail())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_MEMBER);
                });
        Club club = clubRepository.findById(bundle.getClubId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_CLUB);
                });
        Post post = postRepository.findById(bundle.getPostId())
                .orElseThrow(() -> {
                    throw new NonExitsException(ErrorMessage.NOT_EXIST_POST);
                });

        if (!club.equals(member.getClub())) {
            throw new NonIncludeException(ErrorMessage.NON_INCLUDE_CLUB);
        }
        Comment comment = commentConverter.toComment(bundle.getContent());
        comment.add(member, post);
    }

}
