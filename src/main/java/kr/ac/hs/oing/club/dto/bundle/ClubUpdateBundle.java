package kr.ac.hs.oing.club.dto.bundle;

import kr.ac.hs.oing.club.domain.vo.Description;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.member.domain.vo.Email;
import lombok.Builder;

@Builder
public class ClubUpdateBundle {
    private final Email email;
    private final Long clubId;
    private final Image image;
    private final Description description;
    
    public Email getEmail() {
        return email;
    }

    public Long getClubId() {
        return clubId;
    }

    public Image getImage() {
        return image;
    }

    public Description getDescription() {
        return description;
    }
}
