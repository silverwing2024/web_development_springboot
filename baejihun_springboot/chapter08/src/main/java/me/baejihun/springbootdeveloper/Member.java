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

    public void changeName(String name){
        this.name = name;
    }
    /*
        name의 필드값을 바꾸는 단순한 메서드로 setName과 동일 -> 수정에 사용할 예정이므로 메서드명을 직관적으로 지었다.

        만약에 이 메서드가 @Transactional 애너테이션이포함된 메서드에서  호출되면 JPA는
        변경감지(dirty checking) 기능을 통해 엔티티의 필드값이 변경될 때 해당 변경 사항을
        JPA에 자동으로 반영해 줌.

        MemberRepositoryTest.java

    */
    /*
    각 에너테이션의 역할은 현재로서는 member라는 이름의 테이블에 접근하는데 사용하는 객체로 이해.
    이 후 member 테이블과 member 클래스를 매핑하는 코드를 작성 예정

    */
}