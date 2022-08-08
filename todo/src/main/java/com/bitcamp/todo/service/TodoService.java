package com.bitcamp.todo.service;

import com.bitcamp.todo.model.TodoEntity;
import com.bitcamp.todo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j // 로그 남기는 어노테이션
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<TodoEntity> create(final TodoEntity entity){
        // (1) 저장 할 엔티티가 유효한지 확인
        validate(entity);
        repository.save(entity);

        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    private void validate(final TodoEntity entity){
        // 유효성 검증
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() == null){
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }

    // 수정
    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            // 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어쓴다.
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // 데이터 베이스에 새 값을 저장
            repository.save(todo);
        });

        return retrieve(entity.getUserId());
    }

    // 삭제
    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try {
            // 엔티티 삭제
            repository.delete(entity);
        } catch (Exception e) {
            // exception 발생 시 id 와 exception 을 로깅한다.
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        // 새 Todo 리스트를 가져와 리턴
        return retrieve(entity.getUserId());
    }













}

