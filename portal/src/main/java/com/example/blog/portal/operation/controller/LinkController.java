package com.example.blog.portal.operation.controller;

import com.example.blog.common.Result;
import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.entity.operation.Link;
import com.example.blog.portal.operation.Service.LinkService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/operation")
@CacheConfig(cacheNames = RedisCacheNames.LINK)
@RestController("LinkPortalController")
public class LinkController {
    @Resource
    private LinkService linkService;

    @RequestMapping("/links")
    @Cacheable
    public Result listLink() {
        List<Link> linkList = linkService.listLink();
        return Result.ok().put("linkList", linkList);
    }
}
