package me.baejihun.springbootdeveloper;

import me.baejihun.springbootdeveloper.Member;
import me.baejihun.springbootdeveloper.MemberRepository;
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
}
