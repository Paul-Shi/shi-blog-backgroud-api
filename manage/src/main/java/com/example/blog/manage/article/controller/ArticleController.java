package com.example.blog.manage.article.controller;

import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.manage.article.service.ArticleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/article")
@CacheConfig(cacheNames = {RedisCacheNames.RECOMMEND, RedisCacheNames.TAG, RedisCacheNames.ARTICLE, RedisCacheNames.TIMELINE})
public class ArticleController {
    @Resource
    private ArticleService articleService;
}
