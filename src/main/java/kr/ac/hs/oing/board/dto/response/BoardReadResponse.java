package kr.ac.hs.oing.board.dto.response;

import lombok.Builder;

@Builder
public class BoardReadResponse {
    private final Long clubId;
    private final Long boardId;
    private final String name;
    private final String description;

    public Long getClubId() {
        return clubId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
