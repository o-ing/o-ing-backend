package kr.ac.hs.oing.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.board.domain.Board;
import kr.ac.hs.oing.comment.domain.Comment;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.post.domain.vo.Content;
import kr.ac.hs.oing.post.domain.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@AllArgsConstructor
public class Post extends DateEntity {
    @JsonIgnore
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_Id")
    private Board board;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new TreeSet<>();

    protected Post() {
    }

    public void add(Member member, Board board) {
        this.member = member;
        this.board = board;
        member.getPosts().add(this);
        board.getPosts().add(this);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Board getBoard() {
        return board;
    }

    public Member getMember() {
        return member;
    }

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }
}
