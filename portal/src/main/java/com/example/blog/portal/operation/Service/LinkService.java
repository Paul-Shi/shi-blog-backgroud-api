package com.example.blog.portal.operation.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.operation.Link;

import java.util.List;

public interface LinkService extends IService<Link> {
    /**
     * 获取link列表
     *
     * @return
     */
    List<Link> listLink();
}
