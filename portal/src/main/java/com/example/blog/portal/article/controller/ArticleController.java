package com.example.blog.portal.article.controller;

import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.portal.article.service.ArticleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("articlePortalController")
@CacheConfig(cacheNames = {RedisCacheNames.ARTICLE})
public class ArticleController {
    @Resource
    private ArticleService articleService;


}
