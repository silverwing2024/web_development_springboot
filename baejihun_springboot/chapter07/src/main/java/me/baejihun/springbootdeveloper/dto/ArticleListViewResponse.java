package me.baejihun.springbootdeveloper.dto;

import me.baejihun.springbootdeveloper.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
    /*
        다 작성한분들 controller 패키지에
        BlogViewController 파일 만들겠습니다.
     */
}