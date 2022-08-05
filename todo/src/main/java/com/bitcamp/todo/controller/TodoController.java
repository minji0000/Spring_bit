package com.bitcamp.todo.controller;

import com.bitcamp.todo.dto.ResponseDTO;
import com.bitcamp.todo.dto.TodoDTO;
import com.bitcamp.todo.model.TodoEntity;
import com.bitcamp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    /**
     * 생성 (Create Todo 구현)
     */
    @PostMapping()
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            System.out.println("1");
            String temporaryUserId = "temporary-user";

            // (1) TodoEntity 로 변환
            TodoEntity entity = TodoDTO.todoEntity(dto);
            // (2) id를 null로 초기화 한다. 생성 당시에는 ID가 없어야 하기 때문
            entity.setId(null);

            // (3) 임시 유저 아이디를 설정해준다.
            entity.setUserId(temporaryUserId);

            // (4) 서비스를 이용해 entity를 생성한다.
            List<TodoEntity> entities = service.create(entity);

            // (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            // (6) 변환된 TotoDTO 리스트를 이용해 ResponseDTO를 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

            // (7) ResponseDTO 를 리턴

            System.out.println("2");
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
