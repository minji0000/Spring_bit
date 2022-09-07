package com.bitcamp.travelkkaebi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseWrite {

    @Column(name = "category_id")
    private int categoryId;
    private int view;
    private String content;
    private String title;

    public void increaseView(int view) {
        this.view = view + 1;
    }

    public void changeTitleAndContent(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
