package com.example.blog.portal.operation.controller;

import com.example.blog.common.Result;
import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.entity.operation.vo.RecommendVO;
import com.example.blog.portal.operation.Service.RecommendService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("recommendPortalController")
@CacheConfig(cacheNames = RedisCacheNames.RECOMMEND)
@RequestMapping("/operation")
public class RecommendController {
    @Resource
    private RecommendService recommendService;

    @RequestMapping("/recommends")
    @Cacheable(key = "'RECOMMEND'")
    public Result listRecommend() {
        List<RecommendVO> recommendVOS = recommendService.listRecommendVo();
        return Result.ok().put("recommendList", recommendVOS);
    }

    @RequestMapping("/hotReads")
    @Cacheable(key = "'HOTREAD'")
    public Result listHotRead() {
        List<RecommendVO> hotReadList = recommendService.listHotRead();
        return Result.ok().put("hotReadList", hotReadList);
    }
}
