package com.bitcamp.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    /**
     * TodoDTO 뿐만 아니라 이후 다른 모델의 DTO도
     * ResponseDTO 를 이용해 리턴하도록 자바 제네릭 이용
     */
    private String error;
    private List<T> resList;
}
