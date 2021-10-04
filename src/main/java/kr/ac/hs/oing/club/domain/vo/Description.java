package kr.ac.hs.oing.club.domain.vo;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class Description {
    @Column(name = "club_description")
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
