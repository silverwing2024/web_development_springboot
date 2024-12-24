package me.baejihun.springbootdeveloper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers(){

        return memberRepository.findAll();
    }
    /*
    1. MemberRepository라는 번들 주입 받은 후에,
    2. findAll() 메서드를 호출해서 멤버 테이블에 저장된 멤버 목록을 가져옵니다.
    */
}
