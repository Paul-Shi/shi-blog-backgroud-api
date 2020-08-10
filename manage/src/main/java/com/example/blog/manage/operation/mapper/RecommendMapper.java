package com.example.blog.manage.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.operation.Recommend;
import com.example.blog.entity.operation.vo.RecommendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {
    /**
     * 获取推荐文章列表
     * @return
     */
    List<RecommendVO> listSelect();

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listRecommendVo();

    /**
     * 获取热门列表
     * @return
     */
    List<RecommendVO> listHotRead();
}
