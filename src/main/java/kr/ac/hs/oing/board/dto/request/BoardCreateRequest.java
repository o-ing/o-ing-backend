package kr.ac.hs.oing.board.dto.request;

import lombok.Builder;

@Builder
public class BoardCreateRequest {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
