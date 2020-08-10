package com.example.blog.portal.operation.Service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.operation.Tag;
import com.example.blog.entity.operation.vo.TagVO;
import com.example.blog.mapper.operation.TagMapper;
import com.example.blog.portal.operation.Service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TagPortalService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    /**
     * 获取tagVolist
     *
     * @return
     */
    @Override
    public List<TagVO> listTagVo() {
        return baseMapper.listTagVo();
    }
}