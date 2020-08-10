package com.example.blog.portal.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.vo.ArticleVO;

import java.util.Map;

public interface ArticleService extends IService<Article> {
    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取ArticleVo对象
     *
     * @param articleId
     * @return
     */
    ArticleVO getArticleVo(Integer articleId);

    /**
     * 获取简单的Article对象
     *
     * @param articleId
     * @return
     */
    ArticleVO getSimpleArticleVo(Integer articleId);
}
