package kr.ac.hs.oing.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.board.domain.vo.Description;
import kr.ac.hs.oing.board.domain.vo.Name;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.common.domain.DateEntity;
import kr.ac.hs.oing.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@AllArgsConstructor
public class Board extends DateEntity {
    @JsonIgnore
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Description description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_Id")
    private Club club;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new TreeSet<>();

    protected Board() {

    }

    public void addBoard(Club club) {
        club.addBoard(this);
        this.club = club;
    }

    public void update(Name name, Description description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Club getClub() {
        return club;
    }

    public Set<Post> getPosts() {
        return posts;
    }
}