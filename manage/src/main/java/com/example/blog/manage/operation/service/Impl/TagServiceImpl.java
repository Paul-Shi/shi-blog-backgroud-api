package com.example.blog.manage.operation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.common.exception.MyExceptionHandler;
import com.example.blog.common.util.PageUtils;
import com.example.blog.common.util.Query;
import com.example.blog.entity.operation.Tag;
import com.example.blog.entity.operation.TagLink;
import com.example.blog.manage.operation.service.TagService;
import com.example.blog.mapper.operation.TagLinkMapper;
import com.example.blog.mapper.operation.TagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @Autowired
    private TagLinkMapper tagLinkMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Tag> page = baseMapper.selectPage(new Query<Tag>(params).getPage(),
                new QueryWrapper<Tag>().lambda());
        return new PageUtils(page);
    }

    @Override
    public List<Tag> listByLinkId(Integer linkId, Integer type) {
        return this.baseMapper.listByLinkId(linkId, type);
    }

    @Override
    public void saveTagAndNew(List<Tag> tagList, Integer linkId, Integer type) {
        Optional.ofNullable(tagList)
                .ifPresent(tagListValue -> tagListValue.forEach(tag -> {
                    if (tag.getId() == null) {
                        this.baseMapper.insert(tag);
                    }
                    TagLink tagLink = new TagLink(linkId, tag.getId(), type);
                    tagLinkMapper.insert(tagLink);
                }));
    }

    @Override
    public void deleteTagLink(Integer linkId, Integer type) {
        tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
        .eq(linkId != null, TagLink::getLinkId, linkId)
        .eq(type != null, TagLink::getType, type));
    }
}
