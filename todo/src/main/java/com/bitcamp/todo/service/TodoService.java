package com.bitcamp.todo.service;

import com.bitcamp.todo.model.TodoEntity;
import com.bitcamp.todo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<TodoEntity> create(final TodoEntity entity) {
        // (1) 저장할 entity가 유효한지 확인
        validate(entity);
        repository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        // 유효성 검증
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
            // 에러 처리 이유: 프로그램의 비정상적인 종료를 막기 위해
        }
        if (entity.getUserId() == null) {
            log.warn("unknown user");
            throw new RuntimeException("unknown user");
        }
    }

}
