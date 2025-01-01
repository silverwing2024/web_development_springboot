package me.baejihun.SpringBootDeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    MemberRepository memberRepository;
    public List<Member> getAllMembers(){

        return memberRepository.findAll();  //멤버 목록 얻기, 왜 findall()인가에 주목
    }
    /*
    1. 멤버리포지터리 빈을 주입 받은 후에
    2. findAll()메서드를 호출해서 멤버 테이블에 저장된 멤버 목록을 가져옵니다.
        -> 개발자가 직접 정의한 메서드가 아니라 JPARepository라는 스프링부트 관련 클래스에서 메소드를 상속받아 사용하는 경우

    * */
}
