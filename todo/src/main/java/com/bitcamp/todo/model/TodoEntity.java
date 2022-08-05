package com.bitcamp.todo.model;
// JPA 사용 시 쿼리문 안 만들어도 돼
// Entity = Table
// 모델에서 만든 DTO는 그냥 디티오
// 사용자 입력 데이터를 담는 그릇이 DTO
// entity는 테이블 그 자체 !


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@NoArgsConstructor //파라미터 없는 생성자를 만들어주는 것
@AllArgsConstructor
@Data
@Entity //자바클래스를 엔티티로 지정
@Table(name = "todo") //테이블 이름을 지정
public class TodoEntity {

    // 오브젝트 아이디 @Id는 기본 키가 될 필드에 지정한다.
    @Id
    @GeneratedValue(generator = "uuid2") // ID 자동생성(유니크한 아이디 자동 생성)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    // 네트워크 상에서 고유성이 보장되는 id
    private String id; //식별자

    private String userId;
    private String title;
    private boolean done;
}
