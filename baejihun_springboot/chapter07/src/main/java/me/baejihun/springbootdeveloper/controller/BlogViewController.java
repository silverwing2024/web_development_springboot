package me.baejihun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.baejihun.springbootdeveloper.domain.Article;
import me.baejihun.springbootdeveloper.dto.ArticleListViewResponse;
import me.baejihun.springbootdeveloper.dto.ArticleViewResponse;
import me.baejihun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new).toList();

        model.addAttribute("articles", articles);   // "articles"키에 articles list를 담았습니다.

        return "articleList";   // -> 우리가 이거 다음에 만들어야될 파일의 위치 및 파일명
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    /*
    templates => article.html
    */

    @GetMapping("/new-article")
    //1. id 키를 ㄱ ㅏ진 쿼리 패러미터의 값을 id
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id==null){
            model.addAttribute("article", new ArticleViewResponse());
        }
        else {
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}