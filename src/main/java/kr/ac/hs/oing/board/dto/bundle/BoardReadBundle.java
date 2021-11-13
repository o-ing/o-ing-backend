package kr.ac.hs.oing.board.dto.bundle;

public class BoardReadBundle {
    private final Long clubId;

    public BoardReadBundle(Long clubId) {
        this.clubId = clubId;
    }

    public Long getClubId() {
        return clubId;
    }
}
