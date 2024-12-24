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
    private Long id;    //DB 테이블의 'id'칼럼과 매칭

    @Column(name="name", nullable = false)
    private String name;

    /*각 에너테이션의 역할은 Lombok의 경우 java 과정에서 다 설명함 하지만 다시 설명 예정
    * 현재는 member라는 이름의 테이블에 접근하는데 사용하는 객체로 이해하세요.
    * 이후 member 테이블과 Member 클래스를 매핑하는 코드를 작성 예정
    * */
}
