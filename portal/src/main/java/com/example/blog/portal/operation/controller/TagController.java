package com.example.blog.portal.operation.controller;

import com.example.blog.common.Result;
import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.entity.operation.vo.TagVO;
import com.example.blog.portal.operation.Service.TagService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("tagPortalController")
@CacheConfig(cacheNames = RedisCacheNames.TAG)
@RequestMapping("/operation")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    @Cacheable
    public Result listTag() {
        List<TagVO> tagVOList = tagService.listTagVo();
        return Result.ok().put("tagList", tagVOList);
    }
}
