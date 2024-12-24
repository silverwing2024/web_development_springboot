package me.baejihun.springbootdeveloper;
/*
    혹시나 기능 구현을 했는데 작동되지 않는 경우

    1. 서버를 껏다가 켠다.
    2. 그래도 안되는 경우에는 ctrl + s를 눌러서 세이브하고
    3. intelliJ를 껏다가 켬.
    4. 서버 재실행
    5. 그러면 대부분 완료되는 경우가 많음.
*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "Hello World!";
    }
}
/*

*
*/