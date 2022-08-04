package com.bitcamp.todo.dto;

import com.bitcamp.todo.model.TodoEntity;
import com.sun.xml.bind.v2.TODO;
import lombok.Data;

@Data
public class TodoDTO {
    /**
     * 아이템 생성, 수정, 삭제
     */

    private String id;
    private String title;
    private boolean done;

    // 데이터 변형을 막아주기 위해 상수로 선언
    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public static TodoEntity todoEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
