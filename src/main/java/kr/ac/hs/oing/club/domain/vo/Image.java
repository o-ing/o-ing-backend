package kr.ac.hs.oing.club.domain.vo;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.sql.Blob;

// TODO
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Lob
    @Column(name = "club_image", nullable = false)
    private String image;

    public Image(String image) {
        this.image = image;
    }

}
