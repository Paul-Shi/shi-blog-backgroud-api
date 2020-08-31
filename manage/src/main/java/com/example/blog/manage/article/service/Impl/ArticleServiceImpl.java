package com.example.blog.manage.article.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.dto.ArticleDTO;
import com.example.blog.manage.article.service.ArticleService;
import com.example.blog.manage.operation.service.TagService;
import com.example.blog.mapper.article.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void saveArticle(ArticleDTO articleDTO) {

    }

    @Override
    public void deleteBatch(Integer[] articleIds) {

    }

    @Override
    public void updateArticle(ArticleDTO articleDTO) {

    }

    @Override
    public ArticleDTO getAticle(Integer articleId) {
        return null;
    }

    @Override
    public boolean checkByCategory(Integer id) {
        return false;
    }
}
