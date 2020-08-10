package com.example.blog.portal.operation.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.operation.Recommend;
import com.example.blog.entity.operation.vo.RecommendVO;

import java.util.List;

public interface RecommendService extends IService<Recommend> {
    List<RecommendVO> listRecommendVo();

    List<RecommendVO> listHotRead();
}
