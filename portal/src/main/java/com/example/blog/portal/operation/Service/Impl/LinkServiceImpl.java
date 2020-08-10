package com.example.blog.portal.operation.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.operation.Link;
import com.example.blog.mapper.operation.LinkMapper;
import com.example.blog.portal.operation.Service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkPortalService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 获取link列表
     *
     * @return
     */
    @Override
    public List<Link> listLink() {
        return baseMapper.selectList(null);
    }
}
