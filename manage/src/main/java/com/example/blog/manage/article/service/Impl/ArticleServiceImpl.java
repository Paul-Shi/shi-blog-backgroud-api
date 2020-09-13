package com.example.blog.manage.article.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.common.enums.ModuleEnum;
import com.example.blog.common.util.PageUtils;
import com.example.blog.common.util.Query;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.dto.ArticleDTO;
import com.example.blog.entity.article.vo.ArticleVO;
import com.example.blog.entity.operation.Category;
import com.example.blog.manage.article.service.ArticleService;
import com.example.blog.manage.operation.service.CategoryService;
import com.example.blog.manage.operation.service.TagService;
import com.example.blog.mapper.article.ArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ArticleVO> page = new Query<ArticleVO>(params).getPage();
        List<ArticleVO> articleList = baseMapper.listArticleVo(page, params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType,ModuleEnum.ARTICLE.getValue()));
        // 封装ArticleVo
        Optional.ofNullable(articleList).ifPresent((articleVos ->
                articleVos.forEach(articleVo -> {
                    // 设置类别
                    articleVo.setCategoryListStr(categoryService.renderCategoryArr(articleVo.getCategoryId(),categoryList));
                    // 设置标签列表
                    articleVo.setTagList(tagService.listByLinkId(articleVo.getId(),ModuleEnum.ARTICLE.getValue()));
                })));
        page.setRecords(articleList);
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDTO articleDTO) {
        baseMapper.insert(articleDTO);
        tagService.saveTagAndNew(articleDTO.getTagList(), articleDTO.getId(), ModuleEnum.ARTICLE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] articleIds) {
        Arrays.stream(articleIds).forEach(articleId -> {
            tagService.deleteTagLink(articleId, ModuleEnum.ARTICLE.getValue());
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleDTO articleDTO) {
        //删除多对多所属标签
        tagService.deleteTagLink(articleDTO.getId(), ModuleEnum.ARTICLE.getValue());
        //更新所属标签
        tagService.saveTagAndNew(articleDTO.getTagList(), articleDTO.getId(), ModuleEnum.ARTICLE.getValue());
        //更新博文
        baseMapper.updateById(articleDTO);
    }

    @Override
    public ArticleDTO getAticle(Integer articleId) {
        ArticleDTO articleDTO = new ArticleDTO();
        Article article = this.baseMapper.selectById(articleDTO);
        BeanUtils.copyProperties(article, articleDTO);
        //查询所属标签
        articleDTO.setTagList(tagService.listByLinkId(articleId, ModuleEnum.ARTICLE.getValue()));
        return articleDTO;
    }

    @Override
    public boolean checkByCategory(Integer id) {
        return baseMapper.checkByCategory(id) > 0;
    }
}
