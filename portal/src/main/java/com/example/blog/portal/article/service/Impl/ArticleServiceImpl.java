package com.example.blog.portal.article.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.vo.ArticleVO;
import com.example.blog.mapper.article.ArticleMapper;
import com.example.blog.portal.article.service.ArticleService;

import java.util.Map;

public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        return null;
    }

    @Override
    public ArticleVO getArticleVo(Integer articleId) {
        return null;
    }

    @Override
    public ArticleVO getSimpleArticleVo(Integer articleId) {
        return null;
    }
}
