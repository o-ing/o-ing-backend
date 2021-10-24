package kr.ac.hs.oing.comment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.comment.domain.vo.Content;
import kr.ac.hs.oing.member.domain.Member;
import kr.ac.hs.oing.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
public class Comment {
    @JsonIgnore
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_Id")
    private Post post;

    protected Comment() {

    }

    public void add(Member member, Post post) {
        this.member = member;
        this.post = post;
        member.getComments().add(this);
        post.getComments().add(this);
    }

    public Post getPost() {
        return post;
    }
}
