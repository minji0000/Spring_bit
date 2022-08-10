package com.bitcamp.todo.controller;

import com.bitcamp.todo.dto.ResponseDTO;
import com.bitcamp.todo.dto.TodoDTO;
import com.bitcamp.todo.model.TodoEntity;
import com.bitcamp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {


    private final TodoService service;

    /**
     * 생성 (Create Todo 구현)
     */
    @PostMapping
    // 제네릭 와일드카드
    public ResponseEntity<?> createTodo(@AuthenticationPrincipal String userId,  @RequestBody TodoDTO dto) {
        try {
         //   String temporaryUserId = "temporary-user";

            // (1) TodoEntity 로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            // (2) id를 null 로 초기화 한다. 생성 당시에는 id가 없어야 하기 때문
            entity.setId(null);

            // (3) 임시 유저 아이를 설정해준다
            entity.setUserId(userId);

            // (4) 서비스를 이용해 엔티티를 생성한다.
            List<TodoEntity> entities = service.create(entity);

            // (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
            //                                      (entity -> new TodoDTO(entity))
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            // (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO 를 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

            // (7) ResponseDTO 를 리턴
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            // 에러 잡는 이유: 프로그램의 비정삭적인 종료를 막기 위해
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    //조회(Retrieve Todo 구현)
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId) {
        //String temporaryUserId = "temporary-user";

        List<TodoEntity> entities = service.retrieve(userId);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

        return ResponseEntity.ok().body(response);
    }


    //수정(Update Todo 구현)
    @PutMapping
    public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {
        //String temporaryUserId = "temporary-user";

        TodoEntity entity = TodoDTO.toEntity(dto);
        entity.setUserId(userId);
        List<TodoEntity> entities = service.update(entity);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {
        try {
            //String temporaryUserId = "temporary-user";

            TodoEntity entity = TodoDTO.toEntity(dto);
            entity.setUserId(userId);
            List<TodoEntity> entities = service.delete(entity);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().resList(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }


}
