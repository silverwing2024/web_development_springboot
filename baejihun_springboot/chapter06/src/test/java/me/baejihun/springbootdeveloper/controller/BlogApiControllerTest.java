package me.baejihun.springbootdeveloper.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.baejihun.springbootdeveloper.Repository.BlogRepository;
import me.baejihun.springbootdeveloper.domain.Article;
import me.baejihun.springbootdeveloper.dto.AddArticleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetup(){
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 블로그에 글 추가 성공")
    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 객체를 JSON 형태로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated()); // 201 Created로 응답 상태 코드 확인

        List<Article> articles = blogRepository.findAll();  // repository에서 전체 글(객체)들을 가져와 List에 담기
        assertThat(articles.size()).isEqualTo(1);   // 글이 하나만 있는지 확인

        // 첫 번째 글의 title과 content가 예상과 일치하는지 확인
        assertThat(articles.get(0).getTitle()).isEqualTo("title");
        assertThat(articles.get(0).getContent()).isEqualTo("content");
        /*
            List에서 <> 제네릭 Article 클래스의 객체를 요소로 담게 됩니다.
            List 주요 메서드
                .get(인덱스 넘버) -> 0번째 인덱스에 저장된 Article 객체를 확인
            Article 객체의 필드는 title과 content ->  Getter를 사용해서
            getTitle()과 getContent()


            writeValueAsString() 메서드 : 객체를 JSON으로 직렬화.
            그 이후에 MockMvc를 사용해 HTTP 메서드, URL, 요청본문, 요청 타입 등을 설정 뒤 테스트 요청 보냈습니다.
            contentType() 메서드 : JSON / XML 등 다양한 타입 중 하나를 선택할 수 있는데, 저희는 JSON 썻습니다.
            assertThat() 메서드 : 글의 개수가 1인지,
            Article 객체의 field인 title의 값이 "제목"인지,
            Article 객체의 field인 content의 값이 "내용"인지 확인,

            assertThat(articles.size()).isEqualTo(1);   - 블로그의 글 크기가 1이어야 합니다.
            assertThat(articles.size()).isGreaterThan(2);   - 블로그의 글 크기가 2보다 커야 합니다.
            assertThat(articles.size()).isLessThan(5);   - 블로그의 글 크기가 5보다 작아야 합니다.
            assertThat(article.title()).isEqualTo("제목");   - 블로그의 글의 title 값이 "제목"이어야 합니다.
            assertThat(article.title()).isNotEmpty();   - 블로그의 글의 title 값이 비어있지 않아야 합니다.
            assertThat(article.title()).contains("제");   - 블로그의 글의 title 값이 "제"를 포함해야 합니다.

            BlogService.java로 넘어갑니다.
        */
    }
    @DisplayName("findAllArticles: 블로그 글 목록 조회 성공")
    @Test
    public void findAllArticles() throws Exception{
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));

    }

    @DisplayName("findAllArticle: 블로그 글 조회 성공")
    @Test
    public void findArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(url,savedArticle.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }
    /*
      삭제 구현하겠습니다.

        BlogService로 가서 delete() 메서드 추가 -> blogRepository
    */
    // 삭제 메서드
    @DisplayName("deleteArticle : 블로그 글 삭제")
    @Test
    public void deleteArticle() throws Exception {
        // given - 1
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        // given - 2 객체 생성 -> 하나만 생성했다가 지운 다음에 List 전체 불러왔는데 0이면 됩니다.
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when     - CR과 다른 점이 하나 있습니다.
        mockMvc.perform(delete(url, savedArticle.getId()))// findArticle()처럼 하나의 객체를 대상으로 할 때는 argument가 url만 있는게 아닙니다.
                .andExpect(status().isOk());

        // then - 검증단계에서 블로그 글 목록을 전부 다 가지고 올겁니다. -> size()체크 했더니 0 이거나 / .isEmpty() 메서드 활용
        // 전부 다 가지고 오는 단계
        List<Article> articles = blogRepository.findAll();

        // 가지고 온 List에 아무런 값이 없는지 확인하는 단계 -> 왜 아무것도 없냐?
        // Test 메서드는 메서드 단위로 실행된다고 했습니다.
        // given 단계에서 생성한 객체 하나만 있고, 그것을 삭제 했기 때문에
        // articles 내에는 아무런 값도 없어야만 합니다.
        assertThat(articles).isEmpty();
    }


}
