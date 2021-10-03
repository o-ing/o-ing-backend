package kr.ac.hs.oing.club.domain.vo;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@EqualsAndHashCode
public class Image {

    @Lob
    @Column(name = "club_image", nullable = false)
    private String image;

    protected Image() {

    }

    public Image(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
