package com.example.blog.portal.operation.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.operation.Tag;
import com.example.blog.entity.operation.vo.TagVO;

import java.util.List;

public interface TagService extends IService<Tag> {
    /**
     * 获取tagVoList
     *
     * @return
     */
    List<TagVO> listTagVo();
}

