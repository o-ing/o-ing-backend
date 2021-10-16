package kr.ac.hs.oing.board.domain.vo;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@EqualsAndHashCode
public class Description {
    @Lob
    @Column(name = "board_description", nullable = false)
    private String description;

    protected Description() {
    }

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
