package com.bitcamp.travelkkaebi.entity;

import com.bitcamp.travelkkaebi.dto.PickMeDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board_pick_me")
public class PickMeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pick_me_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Embedded
    private BaseWrite baseWrite;

    private boolean company;
    private boolean closed;

    @Column(name = "preferred_region")
    private String region;

    @Column(name = "preferred_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;

    @Column(name = "preferred_end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;

    public static PickMeEntity toEntity(PickMeDTO pickMeDTO, int userId) {
        return PickMeEntity.builder()
                .userEntity(UserEntity.builder()
                        .id(userId)
                        .build())
                .baseWrite(BaseWrite.builder()
                        .categoryId(pickMeDTO.getCategoryId())
                        .content(pickMeDTO.getContent())
                        .title(pickMeDTO.getTitle())
                        .view(pickMeDTO.getView())
                        .build())
                .startDate(pickMeDTO.getPreferredStartDate())
                .endDate(pickMeDTO.getPreferredEndDate())
                .closed(pickMeDTO.isClosed())
                .company(pickMeDTO.isCompany())
                .build();
    }

    public void change(PickMeDTO pickMeDTO) {
        this.baseWrite.changeTitleAndContent(pickMeDTO.getTitle(), pickMeDTO.getContent());
    }
}
