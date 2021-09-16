package kr.ac.hs.oing.club.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.hs.oing.club.domain.vo.Name;
import kr.ac.hs.oing.club.domain.vo.Image;
import kr.ac.hs.oing.club.domain.vo.Branch;
import kr.ac.hs.oing.club.domain.vo.Introduce;
import kr.ac.hs.oing.club.dto.CreateClubDto;
import kr.ac.hs.oing.common.domain.DateEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends DateEntity {

    @JsonIgnore
    @Id
    @Column(name = "club_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    private Name name;

    @Embedded
    private Image image;

    @Embedded
    private Introduce introduce;

    @Enumerated(EnumType.STRING)
    @Column(name = "club_branch")
    private Branch branch;

    public static Club of(CreateClubDto createClubDto) {
        return Club.builder()
                .name(createClubDto.getName())
                .image(createClubDto.getImage())
                .introduce(createClubDto.getIntroduce())
                .branch(createClubDto.getBranch())
                .build();
    }
}
