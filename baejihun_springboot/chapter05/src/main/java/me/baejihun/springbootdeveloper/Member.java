package me.baejihun.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //DB 테이블의 id 컬럼과 매칭

    @Column(name = "name", nullable = false)
    private String name; //DB 테이블의 'name' 컬럼과 매칭

    /*
    각 에너테이션의 역할은 현재로서는 member라는 이름의 테이블에 접근하는데 사용하는 객체로 이해.
    이 후 member 테이블과 member 클래스를 매핑하는 코드를 작성 예정

    */
}