package me.baejihun;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    //여기서 퀴즈
    @DisplayName("quiz(): GET / quiz?code=1이면 응답 코드는 201, 응답 본문은 created!를 반환한다.")
    @Test
    public void getQuiz1() throws Exception {
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "1")
        );
        //then
        result.andExpect(status().isCreated())
                .andExpect(content().string("Created!"));



    }

    @DisplayName("quiz() GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 반환한다.")
    @Test
    public void getQuiz2() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "2")
        );
        //then
        result.andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));


    }

    @DisplayName("quiz() : POST / quiz?code=1이면 응답 코드는 403, 응답 본문은 Forbidden!를 반환한다.")
    @Test
    public void postQuiz1() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1)))
        );

        //then
        result.andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz() : POST / quiz?code=99이면 응답 코드는 200, 응답 본문은 OK!를 반환한다.")
    @Test
    public void postQuiz2() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(99)))
        );

        //then
        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));

    }


}
/*

ObjectMapper : Jackson 라이브러리에서 제공하는 클래스로 객체와 JSON간 변환을 처리

Code code = new Code(13)    //QuizController.java에서 확인
objectMapper.writeAsString(code)

이상의 예시 코드를 분석해보면,
new Code(13)을 통해서 COde 클래스의 code 객체를 생성했고, writeValueAsString(code) 메서드를 통해서 JSON 문자열 형태로 객체가 변환.

이런식으로, 객체 -> JSON 문자열로의 변환을 '객체 직렬화'라고 하기도 합니다.

JSON 문자열로의 변환 결과 예시
{
    'value': 13
}


*/