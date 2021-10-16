package kr.ac.hs.oing.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.board.domain.vo.Content;
import kr.ac.hs.oing.board.domain.vo.Title;
import kr.ac.hs.oing.club.domain.Club;
import kr.ac.hs.oing.common.domain.DateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

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
    private Title title;

    @Embedded
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_Id")
    private Club club;

    protected Board() {

    }

    public void addBoard(Club club) {
        club.addBoard(this);
        this.club = club;
    }
}
