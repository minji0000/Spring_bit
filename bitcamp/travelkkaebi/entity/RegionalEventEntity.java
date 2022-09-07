package com.bitcamp.travelkkaebi.entity;

import com.bitcamp.travelkkaebi.dto.RegionEventDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board_regional_event")
public class RegionalEventEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regional_event_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "poster_image_url")
    private String posterImageUrl;

    @Embedded
    private BaseWrite baseWrite;

    public static RegionalEventEntity toEntity(RegionEventDTO regionEventDTO) {
        return RegionalEventEntity.builder()
                .userEntity(UserEntity.builder()
                        .id(regionEventDTO.getUserId())
                        .nickname(regionEventDTO.getNickname())
                        .build())
                .baseWrite(BaseWrite.builder()
                        .categoryId(regionEventDTO.getCategoryId())
                        .content(regionEventDTO.getContent())
                        .title(regionEventDTO.getTitle())
                        .view(regionEventDTO.getView())
                        .build())
                .posterImageUrl(regionEventDTO.getPosterImageUrl())
                .build();
    }

    public void change(RegionEventDTO regionEventDTO) {
        this.baseWrite.changeTitleAndContent(regionEventDTO.getContent(), regionEventDTO.getTitle());
        this.posterImageUrl = regionEventDTO.getPosterImageUrl();
    }


    public void updateView(int view) {
        this.baseWrite.increaseView(view);
    }
}
