package me.baejihun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.baejihun.springbootdeveloper.domain.Article;
import me.baejihun.springbootdeveloper.dto.AddArticleRequest;
import me.baejihun.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    //HTTP 메서드가 POST 일 때 전달 받은 URL과 동일하면 지금 정의하는 메서드와 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
    /*
        1.@RestController : 클래스에 붙이면 HTTP 응답으로 객체 데이터를 "JSON" 형식으로 반환
        2.@PostMapping() : HTTP 메서드가 POST일 때 요청받은 URL과 동일한 메서드와 매핑.
            지금의 경우 /api/articles는 addArticle() 메서드와 매핑.
        3. @RequestBody : HTTP 요청을 할 때, 응답에 해당하는 값을 @RequestBody애너테이션이 붙은 대상 객체는
           AddArticleRequest에 매핑
        4. ReponseEntitiy.status().body()는 응답 코드로 201, 즉 Created를 응답하고
            테이블에 저장된 객체를 반환합니다.

        200 OK : 요청이 성공적으로 수행되었음
        201 Created : 요청이 성공적으로 수행되었고, 새로운 리소스가 생성되었음
        400 Bad Request : 요청 값이 잘못되어 요청에 실패했음
        403 Forbidden : 권한이 없어 요청에 실패했음
        404 Not Found : 요청 값으로 찾은 리소스가 없어 요청에 실패했음
        500 Internal Server Error : 서버 상에 문제가 있어 요청에 실패했음

        API가 잘 동작하는지 확인하는 테스트를 하나 해볼 예정

            H2 콘솔 활성화

            application.yml로 이동

        windows 키 누르고 -> postman 검색
        HTTP 메서드 : POST
        URL : HTTP//localhost:8080/api/articles
        BODY: raw-> json

        요청 창에
        {
            "title": "제목",
            "content": "내용"
        }

        결과값이 Body에 Pretty모드로 결과를 보여줬습니다.
        -> 여기까지 성공했다면 스프링부트 서버에 저장된 것을 의미합니다.

        여기까지 과정이 HTTP 메서드 POST로 서버에 요청을 한 후에 값을 저장하는 과정에 해당.

        크롬 주소창에 아래를 입력
        localhost:8080/h2-

        SQL statement 입력창에 (SQL 편집기 모양임)
        select * from article
        그리고 Run 눌러서 쿼리 실행
        h2 DB에 저장된 데이터를 확인할 수 있습니다.
        애플리케이션을 실행하면 자동으로생성한 에닡티 내요을 바탕으로
        테이블이 생성되고,
        우리가 요청한 POST 요청에 의해 INSERT 문이 실행되어
        실제로 데이터가 저장되었습니다.

        명일 (2025-01-09) 반복작업을 줄이는테스트 코드 작성
            - 매번 H2 들어가는게 번거로워서 test를 이용할 예정.

    */
}
