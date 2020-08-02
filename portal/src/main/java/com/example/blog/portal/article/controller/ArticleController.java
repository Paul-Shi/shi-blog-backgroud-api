package com.example.blog.portal.article.controller;

import com.example.blog.common.Result;
import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.article.vo.ArticleVO;
import com.example.blog.portal.article.service.ArticleService;
import com.example.blog.portal.common.annotation.LogLike;
import com.example.blog.portal.common.annotation.LogView;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController("articlePortalController")
@CacheConfig(cacheNames = {RedisCacheNames.ARTICLE})
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
    @LogView(type = "article")
    public Result getArticle(@PathVariable Integer articleId) {
        ArticleVO articleVo = articleService.getArticleVo(articleId);
        return Result.ok().put("article", articleVo);
    }

    @PutMapping("/article/like/{id}")
    @LogLike(type = "article")
    public Result likeArticle(@PathVariable Integer id) {
        return Result.ok();
    }

    @GetMapping("/articles")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.queryPageCondition(params);
        return Result.ok().put("page", page);
    }
}
