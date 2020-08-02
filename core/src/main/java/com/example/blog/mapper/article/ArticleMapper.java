package com.example.blog.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询列表
     *
     * @param page
     * @param params
     * @return
     */
    List<ArticleVO> listArticleVo(Page<ArticleVO> page, @Param("params") Map<String, Object> params);

    /**
     * 根据条件查询分页
     *
     * @param page
     * @param params
     * @return
     */
    List<ArticleVO> queryPageCondition(Page<ArticleVO> page, @Param("params") Map<String, Object> params);

    /**
     * 更新阅读记录
     *
     * @param id
     */
    void updateReadNum(Integer id);

    /**
     * 获取简单的对象
     *
     * @param articleId
     * @return
     */
    ArticleVO getSimpleArticleVo(Integer articleId);

    /**
     * 更新点赞记录
     *
     * @param parseInt
     */
    void updateLikeNum(Integer parseInt);

    /**
     * 判断类别下是否有文章
     *
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);
}
