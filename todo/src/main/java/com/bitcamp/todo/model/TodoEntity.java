package com.bitcamp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor //파라미터 없는 생성자를 만들어주는 것
@AllArgsConstructor
@Data
@Entity          //자바클래스를 엔티티로 지정
@Table(name = "Todo") //테이블 이름을 지정
public class TodoEntity {

    @Id
    @GeneratedValue(generator = "uuid2") // ID 자동생성(유니크한 아이디 자동 생성)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    // 네트워크 상에서 고유성이 보장되는 id
    private String id; //식별자

    private String userId;
    private String title;
    private boolean done;
}
