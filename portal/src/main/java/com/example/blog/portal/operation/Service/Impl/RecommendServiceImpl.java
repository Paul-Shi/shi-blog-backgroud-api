package com.example.blog.portal.operation.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.common.enums.ModuleEnum;
import com.example.blog.entity.article.Article;
import com.example.blog.entity.article.vo.ArticleVO;
import com.example.blog.entity.book.BookNote;
import com.example.blog.entity.book.vo.BookNoteVO;
import com.example.blog.entity.operation.Recommend;
import com.example.blog.entity.operation.vo.RecommendVO;
import com.example.blog.manage.operation.mapper.RecommendMapper;
import com.example.blog.portal.article.service.ArticleService;
import com.example.blog.portal.book.service.BookNoteService;
import com.example.blog.portal.operation.Service.RecommendService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("recommendPortalService")
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {
    @Resource
    private ArticleService articleService;

    @Resource
    private BookNoteService bookNoteService;

    @Override
    public List<RecommendVO> listRecommendVo() {
        List<RecommendVO> recommendVoList = this.baseMapper.listRecommendVo();
        return genRecommendList(recommendVoList);
    }

    @Override
    public List<RecommendVO> listHotRead() {
        List<RecommendVO> hotReadList = this.baseMapper.listHotRead();
        genRecommendList(hotReadList);
        hotReadList.get(0).setTop(true);
        return hotReadList;
    }

    private List<RecommendVO> genRecommendList(List<RecommendVO> recommendVOS) {
        recommendVOS.forEach(recommendVO -> {
            if (ModuleEnum.ARTICLE.getValue() == recommendVO.getType()) {
                ArticleVO simpleArticelVo = articleService.getSimpleArticleVo(recommendVO.getLinkId());
                BeanUtils.copyProperties(simpleArticelVo, recommendVO);
                recommendVO.setUrlType("article");
            } else if (ModuleEnum.BOOK_NOTE.getValue() == recommendVO.getType()) {
                BookNoteVO simpleBookNoteVo = bookNoteService.getSimpleBookNoteVo(recommendVO.getLinkId());
                recommendVO.setUrlType("bookNote");
                BeanUtils.copyProperties(simpleBookNoteVo, recommendVO);
            }
        });

        return recommendVOS;
    }
}
