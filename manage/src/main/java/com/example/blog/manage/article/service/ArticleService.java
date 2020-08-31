package com.example.blog.manage.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.dto.ArticleDTO;

import java.util.Map;

public interface ArticleService extends IService<Article> {
    /**
     * 分页查询博文列表
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存博文文章
     *
     * @param articleDTO
     */
    void saveArticle(ArticleDTO articleDTO);

    /**
     * 批量删除
     *
     * @param articleIds
     */
    void deleteBatch(Integer[] articleIds);

    /**
     * 更新博文
     *
     * @param articleDTO
     */
    void updateArticle(ArticleDTO articleDTO);

    /**
     * 获取文章对象
     *
     * @param articleId
     * @return
     */
    ArticleDTO getAticle(Integer articleId);

    boolean checkByCategory(Integer id);
}
